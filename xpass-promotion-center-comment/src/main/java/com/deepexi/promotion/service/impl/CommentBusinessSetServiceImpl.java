package com.deepexi.promotion.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.CommentBusinessLabelGroupSetDAO;
import com.deepexi.promotion.dao.CommentBusinessModelConnectDAO;
import com.deepexi.promotion.dao.CommentBusinessStarSetDAO;
import com.deepexi.promotion.dao.CommentBusinessTextSetDAO;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentTemplateTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.*;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * @ClassName CommentBusinessSetServiceImpl
 * @Description
 * @Author zhoujiawen
 * @Date 2019-05-28 21:05
 */
@Service
@Slf4j
public class CommentBusinessSetServiceImpl implements CommentBusinessSetService {

    @Autowired
    private CommentBusinessModelConnectDAO commentBusinessModelConnectDAO;

    @Autowired
    private CommentBusinessStarSetDAO commentBusinessStarSetDAO;

    @Autowired
    private CommentBusinessLabelGroupSetDAO commentBusinessLabelGroupSetDAO;

    @Autowired
    private CommentBusinessTextSetDAO commentBusinessTextSetDAO;

    /**
     * 文本关联表
     */
    @Autowired
    private CommentBusinessTextSetService commentBusinessTextSetService;
    /**
     * 标签组关联表
     */
    @Autowired
    private CommentBusinessLabelGroupSetService labelGroupSetService;
    /**
     * 评价对象关联表
     */
    @Autowired
    private CommentBusinessModelConnectService commentBusinessModelConnectService;
    /**
     * 评价对象表
     */
    @Autowired
    private CommentModelService commentModelService;
    /**
     * 标签组详细查询
     */
    @Resource
    private CommentLabelGroupService commentLabelGroupService;

    /**
     * 星级关联表
     */
    @Autowired
    private CommentBusinessStarSetService commentBusinessStarSetService;
    /**
     * 星级评价详细
     */
    @Autowired
    private CommentStarTemplateAndLabelService commentStarTemplateAndLabelService;

    @Autowired
    private CommentAppBusinessService commentAppBusinessService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveBusinessReplyTemplate(Long businessId, Long modelId, CommentBusinessReplyInputDTO dto) {

        List<Long> starTemplateList = dto.getStarTemplateList();
        List<Long> labelTemplateList = dto.getLabelTemplateList();
        CommentResourceSupportSetVo supportSetVo = dto.getSupportSettings();
        List<CommentBusinessStarSetDO> starSetDOList = new ArrayList<>();
        List<CommentBusinessLabelGroupSetDO> groupSetDOS = new ArrayList<>();

        CommentBusinessModelConnectQuery query = new CommentBusinessModelConnectQuery();
        query.setBusinessId(businessId);
        query.setModelId(modelId);
        List<CommentBusinessModelConnectDO> connectDOS = commentBusinessModelConnectDAO.listCommentBusinessModelConnects(query);
        if (CollectionUtil.isEmpty(connectDOS)) {
            throw new ApplicationException(ResultEnum.COMMENT_TEMPLATE_NOT_EXIST);
        }

        if (CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode().equals(connectDOS.get(0).getIsReply())) {
            throw new ApplicationException(ResultEnum.REPLY_TEMPLATE_EXIST);
        }

        Long businessModelId = connectDOS.get(0).getId();

        log.info("开始新增星级标签,starTemplateList:{},businessModelId:{}", starTemplateList, businessModelId);
        starTemplateList.forEach(item -> {
            CommentBusinessStarSetDO starSetDO = CommentBusinessStarSetDO.builder().starTemplateId(item).
                    businessModelConnectId(businessModelId).
                    type(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode()).build();
            starSetDOList.add(starSetDO);
        });
        if (CollectionUtil.isNotEmpty(starSetDOList)) {
            commentBusinessStarSetDAO.saveBatch(starSetDOList);
        }

        log.info("开始新增标签组标签，labelTemplateList:{},businessModelId:{}", labelTemplateList, businessModelId);
        labelTemplateList.forEach(item -> {
            CommentBusinessLabelGroupSetDO groupSetDO = CommentBusinessLabelGroupSetDO.builder().
                    businessModelConnectId(businessModelId).
                    labelGroupId(item).
                    type(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode()).
                    build();
            groupSetDOS.add(groupSetDO);
        });
        if (CollectionUtil.isNotEmpty(groupSetDOS)) {
            commentBusinessLabelGroupSetDAO.saveBatch(groupSetDOS);
        }

        log.info("开始保存文本资源设置,supportSetVo:{},businessModelId:{}", supportSetVo, businessModelId);
        CommentBusinessTextSetDO textSetDO = CommentBusinessTextSetDO.builder().
                businessModelConnectId(businessModelId).
                supportSettings(JSONObject.toJSONString(supportSetVo)).
                type(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode()).build();
        commentBusinessTextSetDAO.save(textSetDO);

        log.info("修改业务对象配置回评模板");
        CommentBusinessModelConnectDO connectDO = new CommentBusinessModelConnectDO();
        connectDO.setId(businessModelId);
        connectDO.setIsReply(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode());
        return commentBusinessModelConnectDAO.updateById(connectDO);
    }

    @Override
    public Boolean deleteCommentBusinessStarSetByModel(Long businessId, Long modelId) {

        Long businessModelConnectId;
        CommentBusinessModelConnectQuery query = new CommentBusinessModelConnectQuery();
        query.setBusinessId(businessId);
        query.setModelId(modelId);
        List<CommentBusinessModelConnectDO> connectDOS = commentBusinessModelConnectDAO.listCommentBusinessModelConnects(query);
        if (CollectionUtil.isNotEmpty(connectDOS)) {
            businessModelConnectId = connectDOS.get(0).getId();
            log.info("查询业务对象关联id为：{}", businessModelConnectId);
        } else {
            log.warn("未查询出业务对象关联关系");
            return false;
        }

        // 删除业务对象关联星评
        return commentBusinessStarSetDAO.removeByBusinessModelConnectId(businessModelConnectId);
    }

    @Override
    public List<CommentTemplateDTO> queryTemplateList(CommentBusinessTemplateSetQuery query) {

        if (query.getIsPublishing()) {
            return queryPublishTemplate(query);
        } else {
            return queryCommonTemplate(query);
        }
    }

    /**
     * 发布页面查询模板
     * @param query 参数
     * @return
     */
    private List<CommentTemplateDTO> queryPublishTemplate(CommentBusinessTemplateSetQuery query) {

        //发布的模板
        String cacheValue = RedisUtils.getCache(CommentConstants.TEMPLATE_INFO_KEY, query.getBusinessId().toString());
        //缓存数据为空
        if (StringUtils.isBlank(cacheValue)) {
            final String lock = StringUtils.join(CommentConstants.TEMPLATE_INFO_LOCK_KEY, query.getBusinessId());
            //锁住
            if (RedisUtils.lock(lock)) {
                try {
                    CommentAppBusinessDTO businessDTO = commentAppBusinessService.getCommentAppBusiness(query.getBusinessId());
                    if (businessDTO != null && !businessDTO.getEnable()) {
                        List<CommentTemplateDTO> result = new ArrayList<>();
                        RedisUtils.setCacheAndExpTime(StringUtils.join(CommentConstants.TEMPLATE_INFO_KEY, query.getBusinessId()), result);
                        return result;
                    }
                    List<CommentTemplateDTO> templateList = queryCommonTemplate(query);
                    RedisUtils.setCacheAndExpTime(StringUtils.join(CommentConstants.TEMPLATE_INFO_KEY, query.getBusinessId()), templateList);
                    return templateList;
                } finally {
                    RedisUtils.releaseLock(lock);
                }
            } else {
                //没拿到锁
                try {
                    //休眠500毫秒
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error("休眠异常.", e);
                }
                return queryPublishTemplate(query);
            }
        }
        return JSONObject.parseArray(cacheValue, CommentTemplateDTO.class);
    }

    /**
     * 查询模板基于发布和未发布抽成公共方法
     *
     * @param query 查询对象
     * @return
     */
    private List<CommentTemplateDTO> queryCommonTemplate(CommentBusinessTemplateSetQuery query) {

        //1.业务ID 查询评价对象关联表数据
        CommentBusinessModelConnectQuery connectQuery = new CommentBusinessModelConnectQuery();
        connectQuery.setBusinessId(query.getBusinessId());
        connectQuery.setTenantId(query.getTenantId());
        connectQuery.setModelId(query.getModelId());
        List<CommentBusinessModelConnectDTO> list = commentBusinessModelConnectService.listCommentBusinessModelConnects(connectQuery);
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }
        //2.根据关联表ModelId 批量查评价对象
        List<CommentModelDTO> modelList = commentModelService.listCommentModelByIds(
                list.stream().map(CommentBusinessModelConnectDTO::getModelId).distinct().toArray(Long[]::new));
        Long[] ids = list.stream().map(CommentBusinessModelConnectDTO::getId).distinct().toArray(Long[]::new);
        query.setConnectIds(ids);
        Map<Long, CommentModelDTO> modelMap = new HashMap<>(16);
        Map<Long, CommentBusinessTextSetDTO> textMap = new HashMap<>(16);
        Map<Long, List<CommentLabelGroupDTO>> labelGroupResultMap = new HashMap<>(16);
        Map<Long, List<CommentStarTemplateDTO>> starResultMap = new HashMap<>(16);
        if (modelList != null) {
            modelMap = modelList.stream().collect(Collectors.toMap(CommentModelDTO::getId, x -> x));
        }
        //3.根据关联表主键IDs，批量查询文本设置
        List<CommentBusinessTextSetDTO> textList = commentBusinessTextSetService.findListByConnectIds(query);
        if (textList != null) {
            textMap = textList.stream().collect(Collectors.toMap(CommentBusinessTextSetDTO::getBusinessModelConnectId, x -> x));
        }
        //4.根据关联表主键IDs，批量查询标签组关联信息
        List<CommentBusinessLabelGroupSetDTO> lableGroupConnectList = labelGroupSetService.findListByConnectIds(query);
        //5.标签组关联表获取标签组ids
        CommentLabelGroupQuery commentLabelGroupQuery = new CommentLabelGroupQuery();
        if (lableGroupConnectList != null && lableGroupConnectList.size() > 0) {
            commentLabelGroupQuery.setIds(
                    lableGroupConnectList.stream().map(CommentBusinessLabelGroupSetDTO::getLabelGroupId)
                            .distinct().toArray(Long[]::new));
            commentLabelGroupQuery.setPage(-1);
            //6.根据标签组ids 批量查询标签详细
            List<CommentLabelGroupDTO> labelGroupDTOS = commentLabelGroupService.findPageLabelGroupAndDetail(commentLabelGroupQuery);
            Map<Long, CommentLabelGroupDTO> tempGroupMap = new HashMap<>(16);
            if (labelGroupDTOS != null) {
                tempGroupMap = labelGroupDTOS.stream().collect(Collectors.toMap(CommentLabelGroupDTO::getId, x -> x));
            }
            for (CommentBusinessLabelGroupSetDTO setDTO : lableGroupConnectList) {
                setDTO.setGroupDTO(tempGroupMap.get(setDTO.getLabelGroupId()));
            }
            labelGroupResultMap = lableGroupConnectList.stream()
                    .collect(Collectors.groupingBy(CommentBusinessLabelGroupSetDTO::getBusinessModelConnectId,
                            mapping(CommentBusinessLabelGroupSetDTO::getGroupDTO, toList())));
        }
        //7.查询星级关联信息关系信息
        List<CommentBusinessStarSetDTO> starConnectList = commentBusinessStarSetService.findListByConnectIds(query);
        if (starConnectList != null && starConnectList.size() > 0) {
            CommentStarTemplatePageQuery starTemplateQuery = new CommentStarTemplatePageQuery();
            starTemplateQuery.setIds(starConnectList.stream().map(CommentBusinessStarSetDTO::getStarTemplateId).distinct().toArray(Long[]::new));
            starTemplateQuery.setPage(-1);
            List<CommentStarTemplateDTO> starTemplateDTOS = commentStarTemplateAndLabelService.findList(starTemplateQuery);
            Map<Long, CommentStarTemplateDTO> tempStarDTOMap = new HashMap<>(16);
            if (starTemplateDTOS != null && starTemplateDTOS.size() > 0) {
                tempStarDTOMap = starTemplateDTOS.stream().collect(Collectors.toMap(CommentStarTemplateDTO::getId, value -> value));
            }
            for (CommentBusinessStarSetDTO setDTO : starConnectList) {
                setDTO.setCommentStarTemplateDTO(tempStarDTOMap.get(setDTO.getStarTemplateId()));
            }
            starResultMap = starConnectList.stream()
                    .collect(Collectors.groupingBy(CommentBusinessStarSetDTO::getBusinessModelConnectId,
                            mapping(CommentBusinessStarSetDTO::getCommentStarTemplateDTO, toList())));
        }
        //合并结果集
        return dealWithResult(list, modelMap, textMap, starResultMap, labelGroupResultMap);
    }

    private List<CommentTemplateDTO> dealWithResult(
            List<CommentBusinessModelConnectDTO> list,
            Map<Long, CommentModelDTO> modelMap,
            Map<Long, CommentBusinessTextSetDTO> textMap,
            Map<Long, List<CommentStarTemplateDTO>> starResultMap,
            Map<Long, List<CommentLabelGroupDTO>> labelGroupResultMap
    ) {

        List<CommentTemplateDTO> result = new ArrayList<>();
        for (CommentBusinessModelConnectDTO dto : list) {
            Long id = dto.getId();
            CommentTemplateDTO commentTemplateDTO = new CommentTemplateDTO();
            commentTemplateDTO.setId(id);
            commentTemplateDTO.setUpdateTime(dto.getUpdatedTime());
            commentTemplateDTO.setModelId(dto.getModelId());
            commentTemplateDTO.setAppId(dto.getAppId());
            commentTemplateDTO.setBusinessId(dto.getBusinessId());
            commentTemplateDTO.setIsReply(dto.getIsReply());
            //匹配对象名
            if (modelMap != null && modelMap.get(dto.getModelId()) != null) {
                commentTemplateDTO.setModelName(modelMap.get(dto.getModelId()).getName());
            } else {
                commentTemplateDTO.setModelName(null);
            }
            //匹配文本设置
            if (textMap != null) {
                commentTemplateDTO.setCommentBusinessTextSetVO(textMap.get(dto.getId()));
            }
            //匹配标签组设置
            if (labelGroupResultMap != null && labelGroupResultMap.get(id) != null) {
                List<CommentLabelGroupDTO> filterList = labelGroupResultMap.get(id).stream().filter(x -> x != null).collect(Collectors.toList());
                commentTemplateDTO.setCommentBusinessLabelGroupSetVO(filterList);
            } else {
                commentTemplateDTO.setCommentBusinessLabelGroupSetVO(new ArrayList<>());
            }
            //匹配星级设置
            if (starResultMap != null && starResultMap.get(id) != null) {
                List<CommentStarTemplateDTO> filterList = starResultMap.get(id).stream().filter(x -> x != null).collect(Collectors.toList());
                commentTemplateDTO.setCommentBusinessStarSetVO(filterList);
            } else {
                commentTemplateDTO.setCommentBusinessStarSetVO(new ArrayList<>());
            }
            result.add(commentTemplateDTO);
        }
        return result;
    }

}
