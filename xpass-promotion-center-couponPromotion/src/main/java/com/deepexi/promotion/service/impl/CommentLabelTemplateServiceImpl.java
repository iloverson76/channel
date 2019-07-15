package com.deepexi.promotion.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.promotion.dao.*;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CodeGeneratorEnum;
import com.deepexi.promotion.enums.CommentLabelGroupUpdateTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.manager.UpdateDetailServiceFactory;
import com.deepexi.promotion.service.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentLabelTemplateServiceImpl implements CommentLabelTemplateService {

    @Autowired
    private CommentLabelTemplateDao commentLabelTemplateDao;

    @Autowired
    private CommentLabelTemplateHistoryDao historyDao;

    @Autowired
    private CommentLabelGroupConnectDAO commentLabelGroupConnectDAO;

    @Autowired
    private CommentLabelGroupService commentLabelGroupService;

    @Autowired
    private CommentLabelGroupHistoryDAO commentLabelGroupHistoryDAO;

    @Autowired
    private CommentStarTemplateDetailLabelConnectDAO commentStarTemplateDetailLabelConnectDAO;

    @Autowired
    private CommentStarTemplateService commentStarTemplateService;

    @Autowired
    private CommentStarTemplateDetailService commentStarTemplateDetailService;

    @Autowired
    private CommentStarTemplateHistoryDAO commentStarTemplateHistoryDAO;


    @Override
    public List<CommentLabelTemplateDTO> findPage(CommentLabelTemplateQuery query) {
        if (query.isPage()) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<CommentLabelTemplateDO> pageList = commentLabelTemplateDao.findPage(query);
        List<CommentLabelTemplateDTO> commentLabelTemplateDTOS = ObjectCloneUtils.convertList(pageList, CommentLabelTemplateDTO.class, CloneDirection.OPPOSITE);
        return commentLabelTemplateDTOS;
    }

    @Override
    public List<CommentLabelTemplateHistoryDTO> findHistoryPage(CommentLabelTemplateHistoryQuery query) {
        if (query.isPage()) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<CommentLabelTemplateHistoryDO> histories = historyDao.selectByLabelTemplateIds(Collections.singleton(query.getTemplateId()));
        return histories == null ? Collections.emptyList() : histories.stream()
                .filter(Objects::nonNull)
                .map(this::convert2DTO)
                .collect(Collectors.toList());
    }

    private CommentLabelTemplateHistoryDTO convert2DTO(CommentLabelTemplateHistoryDO x) {
        CommentLabelTemplateHistoryDTO dto = new CommentLabelTemplateHistoryDTO();
        dto.setAppId(x.getAppId());
        dto.setLabelTemplateId(x.getLabelTemplateId());
        dto.setName(x.getLabelTemplateName());
        // todo  可使用工厂获取service,转成前端需要显示的,前端不用维护code与name的映射,这里只有名字更新
//        UpdateDetailService updateDetailService = UpdateDetailServiceFactory.getService(CommentLabelTemplateHistoryDTO.class);
//        String updateDetails = updateDetailService.getUpdateCnNamesByCodes(x.getUpdateType());
//        String updateType = updateDetails + "更新";
        dto.setUpdateType(Integer.valueOf(x.getUpdateType()));
        dto.setUpdateTime(x.getCreatedTime());
        dto.setUpdateBy(x.getCreatedBy());
        return dto;
    }

    @Override
    public CommentLabelTemplateDTO detail(Long id) {
        CommentLabelTemplateDO templateDO = commentLabelTemplateDao.selectById(id);
        return templateDO.clone(CommentLabelTemplateDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean update(Long id, CommentLabelTemplateDTO updateDTO) {
        CommentLabelTemplateDO old = commentLabelTemplateDao.selectById(id);
        CommentLabelTemplateDTO oldDTO = old.clone(CommentLabelTemplateDTO.class);
        String name = updateDTO.getName();
        if (old.getName().equals(name)) {
            return true;
        }
        UpdateDetailService updateDetailService = UpdateDetailServiceFactory.getService(CommentLabelTemplateDTO.class);
        String updateType = updateDetailService.getUpdateCodesDetail(updateDTO, oldDTO);

        CommentLabelTemplateDO existOne = commentLabelTemplateDao.selectByAppIdAndName(updateDTO.getAppId(), updateDTO.getName());
        if (existOne != null && name.equals(existOne.getName())) {
            throw new ApplicationException(ResultEnum.LABEL_TEMPLATE_EXIST);
        }
        commentLabelTemplateDao.selectByAppIdAndName(updateDTO.getAppId(), updateDTO.getName());
        CommentLabelTemplateHistoryDO historyRecord = buildHistoryRecordWhenUpdate(updateType, old);
        old.setName(updateDTO.getName());
        historyDao.save(historyRecord);
        boolean flag = commentLabelTemplateDao.updateById(old);
        List<Long> businessIds = commentLabelTemplateDao.selectBusinessIds(id);
        //修改业务模板 操作缓存
        if (CollectionUtil.isNotEmpty(businessIds)) {
            clearCache(businessIds);
        }
        return flag;
    }

    /**
     * 清除业务关联的缓存
     *
     * @param businessIds 业务id集合
     */
    private void clearCache(List<Long> businessIds) {
        businessIds = businessIds.stream().distinct().collect(Collectors.toList());
        RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, businessIds);
    }

    private CommentLabelTemplateHistoryDO buildHistoryRecordWhenUpdate(String updateType, CommentLabelTemplateDO old) {
        CommentLabelTemplateHistoryDO historyDO = new CommentLabelTemplateHistoryDO();
        historyDO.setLabelTemplateId(old.getId());
        historyDO.setLabelTemplateName(old.getName());
        historyDO.setAppId(old.getAppId());
        historyDO.setUpdateType(updateType);
        historyDO.setHistory(JSONObject.toJSONString(old));
        return historyDO;
    }

    @Transactional
    @Override
    public Boolean create(CommentLabelTemplateDTO createDTO) {
        CommentLabelTemplateDO record = createDTO.clone(CommentLabelTemplateDO.class);
        String appId = createDTO.getAppId();
        String name = createDTO.getName();
        CommentLabelTemplateDO existOne = commentLabelTemplateDao.selectByAppIdAndName(appId, name);
        if (existOne != null && name.equals(existOne.getName())) {
            throw new ApplicationException(ResultEnum.LABEL_TEMPLATE_EXIST);
        }
        record.setCode("");
        boolean success = false;
        if (commentLabelTemplateDao.save(record)) {
            record.setCode(CodeGeneratorEnum.LABEL_TEMPLATE_CODE.getStringCode(record.getId()));
            commentLabelTemplateDao.updateById(record);
            success = true;
        }
        return success;
    }

    @Transactional
    @Override
    public Boolean delete(Long... pks) {

        // 清除标签组内的标签信息
        this.deleteLabelGroupConnect(pks);
        // 清除星评内的标签信息
        this.deleteStarTemplateConnect(pks);
        Set<Long> labelTemplateIds = Arrays.stream(pks).collect(Collectors.toSet());
        List<Long> businessIds = commentLabelTemplateDao.selectBusinessIds(pks);
        commentLabelTemplateDao.removeByIds(labelTemplateIds);
        historyDao.deleteByLabelTemplateIds(labelTemplateIds);
        //清除缓存
        if (CollectionUtil.isNotEmpty(businessIds)) {
            clearCache(businessIds);
        }
        return true;
    }

    /**
     * 清除标签和标签组的关联关系
     * @param pks 标签ids
     */
    private void deleteLabelGroupConnect(Long... pks){
        List<CommentLabelGroupHistoryDO> historyDOS = CollectionUtil.createArrayList();
        List<CommentLabelGroupConnectDO> labelGroupConnectDOS = commentLabelGroupConnectDAO.listCommentLabelGroupConnectsByLabelTemplateIds(pks);
        if(CollectionUtil.isEmpty(labelGroupConnectDOS)){
            return ;
        }
        Long[] labelGroupConnectIds = labelGroupConnectDOS.stream().map(CommentLabelGroupConnectDO::getId).toArray(Long[]::new);
        Long[] labelGroupIds = labelGroupConnectDOS.stream().map(CommentLabelGroupConnectDO::getLabelGroupId).toArray(Long[]::new);
        CommentLabelGroupQuery labelGroupQuery = new CommentLabelGroupQuery();
        labelGroupQuery.setIds(labelGroupIds);
        List<CommentLabelGroupDTO> labelGroupDTOList = commentLabelGroupService.findPageLabelGroupAndDetail(labelGroupQuery);
        labelGroupDTOList.forEach(item -> {
            // 插入更改历史记录
            CommentLabelGroupHistoryDO history = new CommentLabelGroupHistoryDO();
            history.setAppId(item.getAppId());
            history.setHistory(JSONObject.toJSONString(item.getLabelList()));
            history.setLabelGroupId(item.getId());
            history.setLabelGroupName(item.getName());
            history.setUpdateType(CommentLabelGroupUpdateTypeEnum.CONNECT_CHANGE.getCode());
            historyDOS.add(history);
        });
        commentLabelGroupHistoryDAO.saveBatch(historyDOS);
        // 删除标签组关联标签
        commentLabelGroupConnectDAO.deleteCommentLabelGroupConnects(labelGroupConnectIds);
    }

    /**
     * 清除标签和星评的关联关系
     * @param pks 标签ids
     */
    private void deleteStarTemplateConnect(Long...pks){
        List<CommentStarTemplateDetailLabelConnectDO> labelConnectDOS = commentStarTemplateDetailLabelConnectDAO.selectByLabelTemplateIds(pks);
        if(CollectionUtil.isEmpty(labelConnectDOS)){
            return ;
        }
        List<Long> starDetailIds = labelConnectDOS.stream().map(CommentStarTemplateDetailLabelConnectDO::getStarTemplateDetailId).collect(Collectors.toList());
        List<CommentStarTemplateDetailDTO> commentStarTemplateDetailDTOS = commentStarTemplateDetailService.queryByIds(starDetailIds);
        List<CommentStarTemplateHistoryDO> historyDOS = CollectionUtil.createArrayList();
        commentStarTemplateDetailDTOS.forEach(item -> {
            CommentStarTemplateDTO starTemplateDTO = commentStarTemplateService.getStarTemplate(item.getStarTemplateId());
            CommentStarTemplateHistoryDO history = CommentStarTemplateHistoryDO.builder()
                    .appId(starTemplateDTO.getAppId())
                    .starTemplateId(starTemplateDTO.getId())
                    .starTemplateName(starTemplateDTO.getName())
                    .history(JSONObject.toJSONString(starTemplateDTO)).build();
            historyDOS.add(history);
        });
        commentStarTemplateHistoryDAO.saveBatch(historyDOS);
        List<Long> connectIds = labelConnectDOS.stream().map(CommentStarTemplateDetailLabelConnectDO::getId).collect(Collectors.toList());
        commentStarTemplateDetailLabelConnectDAO.removeByIds(connectIds);
    }

}