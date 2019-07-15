package com.deepexi.promotion.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.CommentLabelGroupConnectDAO;
import com.deepexi.promotion.dao.CommentLabelGroupDAO;
import com.deepexi.promotion.dao.CommentLabelGroupHistoryDAO;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentLabelGroupUpdateTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.CommentLabelGroupService;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * @author zhhangyanping
 */
@Service
@Slf4j
public class CommentLabelGroupServiceImpl implements CommentLabelGroupService {


    @Autowired
    private CommentLabelGroupDAO commentLabelGroupDAO;

    @Autowired
    private CommentLabelGroupHistoryDAO commentLabelGroupHistoryDAO;

    @Autowired
    private CommentLabelGroupConnectDAO commentLabelGroupConnectDAO;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateCommentLabelGroup(Long id, CommentLabelGroupInputDTO dto) {
        //修改前同名校验
        if (StringUtil.isNotBlank(dto.getName())) {
            if (hasSameName(dto.getName(), dto.getAppId(), id)) {
                log.info("当前应用:{}已存在标签组名称：{}", dto.getAppId(), dto.getName());
                throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
            }
        }
        //  记录修改类型
        Integer updateType = null;
        // 查询标签组明细
        CommentLabelGroupDO labelGroup = commentLabelGroupDAO.getCommentLabelGroup(id);
        //1.名称更新
        if (!labelGroup.getName().equals(dto.getName())) {
            log.info("开始更新标签分类基本信息···");
            CommentLabelGroupDO group = dto.clone(CommentLabelGroupDO.class);
            group.setId(id);
            commentLabelGroupDAO.updateById(group);
            updateType = CommentLabelGroupUpdateTypeEnum.NAME_CHANGE.getCode();
        }

        CommentLabelDetailQuery query = new CommentLabelDetailQuery();
        query.setLabelGroupId(id);
        List<CommentLabelDetailDO> commentLabelDetails = commentLabelGroupDAO.findLabelDetail(query);
        List<Long> updateLabelIds = dto.getLabelIdsList();
        if (commentLabelDetails != null && updateLabelIds != null) {
            //原有的标签ids
            Long[] orgLabelIds = commentLabelDetails.stream().
                    map(CommentLabelDetailDO::getLabelTemplateId).
                    toArray(Long[]::new);
            //新的标签ids
            Long[] newLabelIds = (Long[]) updateLabelIds.stream().toArray(Long[]::new);
            boolean isEquals = Arrays.equals(orgLabelIds, newLabelIds);
            //相等不做操作 不相等删除原有的 新增现有的
            if (!isEquals) {
                Long[] connectIds = commentLabelDetails.stream().map(CommentLabelDetailDO::getConnectId).toArray(Long[]::new);
                // 删除标签组标签关联
                if (connectIds.length > 0) {
                    commentLabelGroupConnectDAO.deleteCommentLabelGroupConnects(connectIds);
                }
                //用户添加了标签
                this.insertCommentLabelGroupConnect(id, dto.getAppId(), updateLabelIds);
                if (updateType != null) {
                    updateType = CommentLabelGroupUpdateTypeEnum.NAME_CONNECT_CHANGE.getCode();
                } else {
                    updateType = CommentLabelGroupUpdateTypeEnum.CONNECT_CHANGE.getCode();
                }
            }
        } else if (commentLabelDetails == null && updateLabelIds != null) {
            //用户新增加标签
            this.insertCommentLabelGroupConnect(id, dto.getAppId(), updateLabelIds);
            if (updateType != null) {
                updateType = CommentLabelGroupUpdateTypeEnum.NAME_CONNECT_CHANGE.getCode();
            } else {
                updateType = CommentLabelGroupUpdateTypeEnum.CONNECT_CHANGE.getCode();
            }
        } else if (commentLabelDetails != null && updateLabelIds == null) {
            // 删除标签组标签关联
            Long[] connectIds = commentLabelDetails.stream().map(CommentLabelDetailDO::getConnectId).toArray(Long[]::new);
            commentLabelGroupConnectDAO.deleteCommentLabelGroupConnects(connectIds);
            if (updateType != null) {
                updateType = CommentLabelGroupUpdateTypeEnum.NAME_CONNECT_CHANGE.getCode();
            } else {
                updateType = CommentLabelGroupUpdateTypeEnum.CONNECT_CHANGE.getCode();
            }
        }
        if (updateType == null) {
            return false;
        } else {
            // 插入更改历史记录
            CommentLabelGroupHistoryDO history = new CommentLabelGroupHistoryDO();
            history.setAppId(dto.getAppId());
            if (CollectionUtil.isEmpty(commentLabelDetails)) {
                history.setHistory("[]");
            } else {
                history.setHistory(JSONObject.toJSONString(commentLabelDetails));
            }
            history.setLabelGroupId(id);
            history.setLabelGroupName(labelGroup.getName());
            history.setUpdateType(updateType);
            boolean flag = commentLabelGroupHistoryDAO.save(history);
            List<Long> businessIds = commentLabelGroupConnectDAO.selectBusinessIds(id);
            RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, businessIds);
            return flag;
        }
    }

//    public Boolean updateCommentLabelGroupBak(Long id, CommentLabelGroupInputDTO dto) {
//        //修改前同名校验
//        if (StringUtil.isNotBlank(dto.getName())) {
//            if (hasSameName(dto.getName(), dto.getAppId(), id)) {
//                log.info("当前应用:{}已存在标签组名称：{}", dto.getAppId(), dto.getName());
//                throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
//            }
//        }
//        //  记录修改类型
//        Integer updateType = null;
//        // 查询标签组明细
//        CommentLabelGroupDO labelGroup = commentLabelGroupDAO.getCommentLabelGroup(id);
//        //1.名称更新
//        if (!labelGroup.getName().equals(dto.getName())) {
//            log.info("开始更新标签分类基本信息···");
//            CommentLabelGroupDO group = new CommentLabelGroupDO();
//            group.setName(dto.getName());
//            group.setId(id);
//            commentLabelGroupDAO.updateById(group);
//            updateType = CommentLabelGroupUpdateTypeEnum.NAME_CHANGE.getCode();
//        }
//
//        // 记录需要修改的标签Ids
//        List<Long> insertLabelIds = null;
//        // 记录需要删除的标签关联关系Ids
//        Long[] deleteConnectIds = null;
//        CommentLabelDetailQuery query = new CommentLabelDetailQuery();
//        query.setLabelGroupId(id);
//        List<CommentLabelDetailDO> commentLabelDetails = commentLabelGroupDAO.findLabelDetail(query);
//        List<Long> updateLabelIds = dto.getLabelIdsList();
//        List<Long> existLabelIds = commentLabelDetails.stream().
//                map(CommentLabelDetailDO::getLabelTemplateId).
//                collect(toList());
//        if (CollectionUtil.isEmpty(commentLabelDetails)) {
//            insertLabelIds = updateLabelIds;
//        } else if (CollectionUtil.isEmpty(updateLabelIds)) {
//            deleteConnectIds = commentLabelDetails.stream().
//                    map(CommentLabelDetailDO::getConnectId).toArray(Long[]::new);
//        } else {
//            // 隔离出需要新增的标签id集合
//            insertLabelIds = updateLabelIds.stream().filter(item -> !existLabelIds.contains(item)).collect(toList());
//            // 隔离出需要删除的标签关联Id
//            deleteConnectIds = commentLabelDetails.stream().
//                    filter(item -> !updateLabelIds.contains(item.getLabelTemplateId())).
//                    collect(toList()).stream().map(CommentLabelDetailDO::getConnectId).toArray(Long[]::new);
//        }
//        if (CollectionUtil.isNotEmpty(insertLabelIds) || deleteConnectIds != null) {
//            if (updateType != null) {
//                updateType = CommentLabelGroupUpdateTypeEnum.NAME_CONNECT_CHANGE.getCode();
//            } else {
//                updateType = CommentLabelGroupUpdateTypeEnum.CONNECT_CHANGE.getCode();
//            }
//        }
//
//        if (CollectionUtil.isNotEmpty(insertLabelIds)) {
//            //用户新增加标签
//            this.insertCommentLabelGroupConnect(id, dto.getAppId(), insertLabelIds);
//        }
//        if (deleteConnectIds != null) {
//            // 用户删除标签
//            commentLabelGroupConnectDAO.deleteCommentLabelGroupConnects(deleteConnectIds);
//        }
//
//        if (updateType == null) {
//            return false;
//        } else {
//            // 插入更改历史记录
//            CommentLabelGroupHistoryDO history = new CommentLabelGroupHistoryDO();
//            history.setAppId(dto.getAppId());
//            if (CollectionUtil.isEmpty(commentLabelDetails)) {
//                history.setHistory("[]");
//            } else {
//                history.setHistory(JSONObject.toJSONString(commentLabelDetails));
//            }
//            history.setLabelGroupId(id);
//            history.setLabelGroupName(labelGroup.getName());
//            history.setUpdateType(updateType);
//            boolean flag = commentLabelGroupHistoryDAO.save(history);
//            //查看当前标签组是否有绑定业务
//            List<Long> businessIds = commentLabelGroupConnectDAO.selectBusinessIds(id);
//            RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, businessIds);
//            return flag;
//        }
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertCommentLabelGroup(CommentLabelGroupInputDTO dto) {
        //APPID下修改前同名校验
        if (hasSameName(dto.getName(), dto.getAppId(), null)) {
            log.info("当前应用:{}已存在标签组名称：{}", dto.getAppId(), dto.getName());
            throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
        }
        // 插入标签组基本信息
        CommentLabelGroupDO commentLabelGroup = dto.clone(CommentLabelGroupDO.class);
        commentLabelGroupDAO.insertCommentLabelGroup(commentLabelGroup);

        Long id = commentLabelGroup.getId();
        Long appId = dto.getAppId();
        List<Long> labelIds = dto.getLabelIdsList();
        return this.insertCommentLabelGroupConnect(id, appId, labelIds);
    }

    @Override
    public Boolean insertCommentLabelGroupConnect(Long groupId, Long appId, List<Long> lableIds) {
        List<CommentLabelGroupConnectDO> commentLabelGroupConnects = new ArrayList<>();
        lableIds.forEach(lableId -> {
            CommentLabelGroupConnectDO commentLabelGroupConnectDO = new CommentLabelGroupConnectDO();
            commentLabelGroupConnectDO.setAppId(appId);
            commentLabelGroupConnectDO.setLabelGroupId(groupId);
            commentLabelGroupConnectDO.setLabelTemplateId(lableId);
            commentLabelGroupConnects.add(commentLabelGroupConnectDO);
        });
        log.info("开始新增标签组标签关联关系，commentLabelGroupConnects：{}", commentLabelGroupConnects);
        return commentLabelGroupConnectDAO.saveBatch(commentLabelGroupConnects);
    }


    @Override
    public Boolean deleteCommentLabelGroups(Long... ids) {
        //删除组的时候也要删除组的历史记录
        boolean flag = commentLabelGroupDAO.deleteCommentLabelGroups(ids);
        if (flag) {
            commentLabelGroupHistoryDAO.deleteBatchByGroupsIds(ids);
        }
        return flag;
    }

    @Override
    public List<CommentLabelGroupDTO> findPageLabelGroupAndDetail(CommentLabelGroupQuery query) {
        //标签名搜索
        if (query.getLabelName() != null) {
            CommentLabelDetailQuery labelQuery = new CommentLabelDetailQuery();
            labelQuery.setLabelName(query.getLabelName());
            List<CommentLabelDetailDO> labelList = commentLabelGroupDAO.findLabelDetail(labelQuery);
            if (labelList != null && labelList.size() > 0) {
                Long[] groupIds = labelList.stream().map(CommentLabelDetailDO::getLabelGroupId).distinct().toArray(Long[]::new);
                query.setIds(groupIds);
            } else {
                return null;
            }
        }
        //分页查询标签组信息
        if (query.getPage() != null && query.getPage() != -1) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<CommentLabelGroupDO> groupList = commentLabelGroupDAO.listPageCommentLabelGroups(query);
        if (groupList == null || groupList.size() == 0) {
            return null;
        }
        //根据ids去查标签详细，批量查询关联信息
        CommentLabelDetailQuery labelQuery = new CommentLabelDetailQuery();
        labelQuery.setIds(groupList.stream().map(CommentLabelGroupDO::getId).distinct().toArray(Long[]::new));
        List<CommentLabelDetailDO> labelList = commentLabelGroupDAO.findLabelDetail(labelQuery);
        if (labelList == null || labelList.size() == 0) {
            return ObjectCloneUtils.convertList(
                    groupList,
                    CommentLabelGroupDTO.class,
                    CloneDirection.OPPOSITE
            );
        }
        for (int i = 0; i < groupList.size(); i++) {
            Long num = groupList.get(i).getId();
            groupList.get(i).setLabelList(labelList.stream()
                    .filter(
                            x -> x.getLabelGroupId().equals(num)
                    ).collect(toList())
            );
        }
        return ObjectCloneUtils.convertList(
                groupList,
                CommentLabelGroupDTO.class,
                CloneDirection.OPPOSITE
        );

    }


    /**
     * 同名校验
     *
     * @param name  名称
     * @param appId 应用ID
     * @return true 有同名 false 无同名
     */
    private boolean hasSameName(String name, Long appId, Long id) {
        Integer n = commentLabelGroupDAO.findSameNameList(name, appId, id);
        log.info("当前有:{}条记录", n);
        if (n <= 0) {
            return false;
        } else {
            return true;
        }
    }
}