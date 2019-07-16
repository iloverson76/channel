package com.deepexi.promotion.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.common.ListCompareResult;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.*;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CodeGeneratorEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.mapper.CommentStarTemplateMapper;
import com.deepexi.promotion.service.CommentStarTemplateService;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zhoust
 * @date 2019/5/24
 **/
@Service
@Slf4j
public class CommentStarTemplateServiceImpl extends ServiceImpl<CommentStarTemplateMapper, CommentStarTemplateDO> implements CommentStarTemplateService {
    @Autowired
    private CommentStarTemplateDAO commentStarTemplateDAO;
    @Autowired
    private CommentStarTemplateHistoryDAO historyDAO;
    @Autowired
    private CommentStarTemplateDetailDAO commentStarTemplateDetailDAO;
    @Autowired
    private CommentStarTemplateDetailLabelConnectDAO commentStarTemplateDetailLabelConnectDAO;
    @Autowired
    private CommentLabelTemplateDao commentLabelTemplateDao;

    @Transactional
    @Override
    public Boolean create(CommentStarTemplateDTO dto) {
        CommentStarTemplateDO existOne = commentStarTemplateDAO.selectByAppIdAndName(dto.getAppId(),dto.getName());
        if (existOne != null && existOne.getName().equals(dto.getName())){
            throw new ApplicationException(ResultEnum.STAR_TEMPLATE_NAME_EXIST);
        }
        CommentStarTemplateDO starTemplate = dto.clone(CommentStarTemplateDO.class);
        starTemplate.setCode("");
        commentStarTemplateDAO.save(starTemplate);
        String code = CodeGeneratorEnum.STAR_TEMPLATE_CODE.getStringCode(starTemplate.getId());
        starTemplate.setCode(code);
        commentStarTemplateDAO.updateById(starTemplate);

        List<CommentStarTemplateDetailDTO> details = dto.getDetails();
        //等级详情项
        List<CommentStarTemplateDetailDO> detailDOList = new ArrayList<>();
        //等级详情项关联的标签
        List<CommentStarTemplateDetailLabelConnectDO> detailLabelConnectDOList = new ArrayList<>();

        fillDetailListAndLabelConnectList(starTemplate.getId(), starTemplate.getAppId(), details, detailDOList, detailLabelConnectDOList);
        commentStarTemplateDetailDAO.saveBatch(detailDOList);
        Map<Long, Long> insertSequenceIdMap = detailDOList.stream().collect(Collectors.toMap(CommentStarTemplateDetailDO::getInsertSequence, CommentStarTemplateDetailDO::getId));
        detailLabelConnectDOList.forEach(x -> {
            Long detailId = insertSequenceIdMap.get(x.getInsertSequence());
            x.setStarTemplateDetailId(detailId);
        });
        commentStarTemplateDetailLabelConnectDAO.saveBatch(detailLabelConnectDOList);
        return true;
    }

    private void fillDetailListAndLabelConnectList(Long starTemplateId,
                                                   Long appId,
                                                   List<CommentStarTemplateDetailDTO> details,
                                                   List<CommentStarTemplateDetailDO> detailDOList,
                                                   List<CommentStarTemplateDetailLabelConnectDO> detailLabelConnectDOList) {
        Long insertSequence = 0L;
        if (CollectionUtils.isNotEmpty(details)) {
            for (CommentStarTemplateDetailDTO detail : details) {
                insertSequence++;
                CommentStarTemplateDetailDO detailDO = CommentStarTemplateDetailDO.builder()
                        .starTemplateId(starTemplateId).appId(appId)
                        .nickName(detail.getNickName()).value(detail.getValue())
                        .supportSettings(JSONObject.toJSONString(detail.getSupportSettings()))
                        .build();
                detailDO.setInsertSequence(insertSequence);
                detailDOList.add(detailDO);
                if (CollectionUtils.isNotEmpty(detail.getLabels())) {
                    for (CommentLabelTemplateDTO label : detail.getLabels()) {
                        CommentStarTemplateDetailLabelConnectDO detailLabelConnectDO = CommentStarTemplateDetailLabelConnectDO.builder()
                                .appId(detailDO.getAppId()).starTemplateDetailId(detailDO.getId())
                                .labelTemplateId(label.getId())
                                .build();
                        detailLabelConnectDO.setInsertSequence(insertSequence);
                        detailLabelConnectDOList.add(detailLabelConnectDO);
                    }
                }
            }
        }
    }

    @Transactional
    @Override
    public Boolean update(CommentStarTemplateDTO update) {
        final CommentStarTemplateDTO old = getStarTemplate(update.getId());
        CommentStarTemplateDO existOne = commentStarTemplateDAO.selectByAppIdAndName(update.getAppId(), update.getName());
        if (existOne != null && existOne.getId().equals(update.getId()) && existOne.getName().equals(existOne.getName())){
            throw new ApplicationException(ResultEnum.STAR_TEMPLATE_NAME_EXIST);
        }
        List<CommentStarTemplateDetailDTO> updateDetails = update.getDetails();
        List<CommentStarTemplateDetailDTO> oldDetails = old.getDetails();
        List<CommentStarTemplateDetailDO> createDetailList = new ArrayList<>();
        List<CommentStarTemplateDetailDO> updateDetailList;
        List<CommentStarTemplateDetailDO> deleteDetailList;
        //新增关联标签
        List<CommentStarTemplateDetailLabelConnectDO> createLabelConnectList = new ArrayList<>();
        //删除关联标签
        List<CommentStarTemplateDetailLabelConnectDO> deleteLabelConnectList = new ArrayList<>();

        Map<Long, CommentStarTemplateDetailDTO> updateDetailMap = updateDetails.stream().filter(x -> x.getId() != null).collect(Collectors.toMap(CommentStarTemplateDetailDTO::getId, x -> x));

        ListCompareResult<CommentStarTemplateDetailDTO> detailsCompareResult = ListCompareResult.compareLists(oldDetails, updateDetails,
                CommentStarTemplateDetailDTO::getId,
                (oldDetail, updateDetail) -> {
                    Set<Long> oldLabels = oldDetail.getLabels().stream().map(CommentLabelTemplateDTO::getId).collect(Collectors.toSet());
                    Set<Long> newLabels = updateDetail.getLabels().stream().map(CommentLabelTemplateDTO::getId).collect(Collectors.toSet());
                    Boolean flag = CollectionUtils.isEqualCollection(oldLabels, newLabels) && oldDetail.getNickName().equals(updateDetail.getNickName()) &&
                            oldDetail.getValue().equals(updateDetail.getValue());
                    CommentStarTemplateDetailSupportSettingsDTO oldSetting = oldDetail.getSupportSettings();
                    CommentStarTemplateDetailSupportSettingsDTO updateSetting = updateDetail.getSupportSettings();
                    Boolean settingFlag = oldSetting.getSupportPicture().equals(updateSetting.getSupportPicture()) &&
                            oldSetting.getSupportText().equals(updateSetting.getSupportText()) &&
                            oldSetting.getSupportVideo().equals(updateSetting.getSupportVideo());
                    return !( flag && settingFlag);
                });
        List<CommentStarTemplateDetailDTO> toCreate = detailsCompareResult.getToCreate().stream().collect(Collectors.toList());
        List<CommentStarTemplateDetailDTO> toUpdate = detailsCompareResult.getToUpdate();
        List<CommentStarTemplateDetailDTO> toDelete = detailsCompareResult.getToDelete();
        //新增等级详情项的部分，标签也必然要新增关联
        fillDetailListAndLabelConnectList(old.getId(), old.getAppId(), toCreate, createDetailList, createLabelConnectList);
        //删除等级详情项的部分，标签关联也必然删除
        fillDeleteLabelConnectList(toDelete,deleteLabelConnectList);
        Map<Long, CommentStarTemplateDetailDTO> shouldUpdateMap = toUpdate.stream()
                .collect(Collectors.toMap(CommentStarTemplateDetailDTO::getId, x -> x));
        Map<Long, CommentStarTemplateDetailDTO> oldDetailDTOMap = oldDetails.stream()
                .collect(Collectors.toMap(CommentStarTemplateDetailDTO::getId, x -> x));
        for (Map.Entry<Long, CommentStarTemplateDetailDTO> entry : shouldUpdateMap.entrySet()) {
            CommentStarTemplateDetailDTO oldDetail = oldDetailDTOMap.get(entry.getKey());
            if (oldDetail != null) {
                CommentStarTemplateDetailDTO updateDetail = updateDetailMap.get(entry.getKey());
                List<Long> oldLabelList = oldDetail.getLabels().stream().map(CommentLabelTemplateDTO::getId).collect(Collectors.toList());
                List<Long> newLabelList = updateDetail.getLabels().stream().map(CommentLabelTemplateDTO::getId).collect(Collectors.toList());
                ListCompareResult<Long> labelCompareResult = ListCompareResult.compareLists(oldLabelList, newLabelList, x -> x, (oldLabel, newLabel) -> false);
                List<Long> createLabels = labelCompareResult.getToCreate();
                List<Long> deleteLabels = labelCompareResult.getToDelete();
                //已有的等级详情项，但是需要新增关联标签
                List<CommentStarTemplateDetailLabelConnectDO> createCollects = createLabels.stream().map(x -> CommentStarTemplateDetailLabelConnectDO.builder()
                        .starTemplateDetailId(entry.getKey())
                        .labelTemplateId(x)
                        .appId(oldDetail.getAppId()).build()).collect(Collectors.toList());
                //已有的等级详情项，但是需要删除关联标签
                List<CommentStarTemplateDetailLabelConnectDO> deleteCollects = deleteLabels.stream().map(x -> CommentStarTemplateDetailLabelConnectDO.builder()
                        .starTemplateDetailId(entry.getKey())
                        .labelTemplateId(x)
                        .appId(oldDetail.getAppId()).build()).collect(Collectors.toList());
                createLabelConnectList.addAll(createCollects);
                deleteLabelConnectList.addAll(deleteCollects);
            }
        }
        updateDetailList = toUpdate.stream()
                .map(x -> {
                    CommentStarTemplateDetailDTO updateDetail = updateDetailMap.get(x.getId());
                    if (updateDetail != null) {
                        CommentStarTemplateDetailDO detail = CommentStarTemplateDetailDO.builder()
                                .nickName(updateDetail.getNickName())
                                .value(updateDetail.getValue())
                                .appId(updateDetail.getAppId())
                                .starTemplateId(updateDetail.getStarTemplateId())
                                .supportSettings(JSONObject.toJSONString(updateDetail.getSupportSettings()))
                                .build();
                        detail.setId(updateDetail.getId());
                        return detail;
                    }
                    return null;
                })
                .filter(Objects::nonNull).collect(Collectors.toList());
        deleteDetailList = toDelete.stream()
                .map(x -> {
                    CommentStarTemplateDetailDO detail = CommentStarTemplateDetailDO.builder()
                            .starTemplateId(x.getStarTemplateId()).build();
                    detail.setId(x.getId());
                    return detail;
                })
                .collect(Collectors.toList());
        if (old.getName().equals(update.getName())
                && old.getEnableGlobalInputBox().equals(update.getEnableGlobalInputBox())
                &&CollectionUtils.isEmpty(createDetailList)
                && CollectionUtils.isEmpty(updateDetailList)
                && CollectionUtils.isEmpty(deleteDetailList)
                && CollectionUtils.isEmpty(createLabelConnectList)
                && CollectionUtils.isEmpty(deleteLabelConnectList)){
            return true;
        }
        CommentStarTemplateDO record = update.clone(CommentStarTemplateDO.class);
        CommentStarTemplateHistoryDO history = CommentStarTemplateHistoryDO.builder()
                .appId(old.getAppId())
                .starTemplateId(old.getId())
                .starTemplateName(old.getName())
                .history(JSONObject.toJSONString(old)).build();
        updateData(record, history, createDetailList, updateDetailList, deleteDetailList, createLabelConnectList, deleteLabelConnectList);
        List<Long> businessIds = commentStarTemplateDAO.selectBusinessIds(Lists.newArrayList(update.getId()));
        RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY,businessIds);
        return true;
    }

    private void fillDeleteLabelConnectList(List<CommentStarTemplateDetailDTO> toDelete,
                                            List<CommentStarTemplateDetailLabelConnectDO> deleteLabelConnectList) {
        for (CommentStarTemplateDetailDTO delete : toDelete){
            List<CommentLabelTemplateDTO> deleteLabels = delete.getLabels();
            if (CollectionUtils.isNotEmpty(deleteLabels)){
                List<CommentStarTemplateDetailLabelConnectDO> deleteConnect = deleteLabels.stream()
                        .map(x -> CommentStarTemplateDetailLabelConnectDO.builder()
                                .starTemplateDetailId(delete.getId())
                                .labelTemplateId(x.getId())
                                .build())
                        .collect(Collectors.toList());
                deleteLabelConnectList.addAll(deleteConnect);
            }
        }

    }

    private void updateData(CommentStarTemplateDO record,
                            CommentStarTemplateHistoryDO historyDO,
                            List<CommentStarTemplateDetailDO> toCreate,
                            List<CommentStarTemplateDetailDO> toUpdate,
                            List<CommentStarTemplateDetailDO> toDelete,
                            List<CommentStarTemplateDetailLabelConnectDO> createLabelConnectList,
                            List<CommentStarTemplateDetailLabelConnectDO> deleteLabelConnectList) {
        commentStarTemplateDAO.updateById(record);
        historyDAO.save(historyDO);
        if (CollectionUtils.isNotEmpty(toCreate)) {
            commentStarTemplateDetailDAO.saveBatch(toCreate);
            Map<Long, Long> insertSequenceIdMap = toCreate.stream().collect(Collectors.toMap(CommentStarTemplateDetailDO::getInsertSequence, CommentStarTemplateDetailDO::getId));
            createLabelConnectList.forEach(x -> {
                if (x.getStarTemplateDetailId() == null) {
                    Long detailId = insertSequenceIdMap.get(x.getInsertSequence());
                    x.setStarTemplateDetailId(detailId);
                }
            });
        }
        if (CollectionUtils.isNotEmpty(createLabelConnectList)){
            commentStarTemplateDetailLabelConnectDAO.saveBatch(createLabelConnectList);
        }
        if (CollectionUtils.isNotEmpty(toUpdate)) {
            commentStarTemplateDetailDAO.updateBatchById(toUpdate);
        }
        if (CollectionUtils.isNotEmpty(toDelete)) {
            Set<Long> deleteIds = toDelete.stream().map(CommentStarTemplateDetailDO::getId).collect(Collectors.toSet());
            commentStarTemplateDetailDAO.removeByIds(deleteIds);
            commentStarTemplateDetailLabelConnectDAO.removeByDO(new HashSet<>(deleteLabelConnectList), record.getTenantId());
        }
    }

    @Override
    public List<CommentStarTemplateDTO> selectPage(CommentStarTemplatePageQuery query) {
        if (query.isPage()) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<CommentStarTemplateDO> templateList = commentStarTemplateDAO.findPae(query);
        if (CollectionUtils.isEmpty(templateList)) {
            return new ArrayList<>();
        }
        return ObjectCloneUtils.convertList(templateList, CommentStarTemplateDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public List<CommentStarTemplateHistoryDTO> selectHistoryPage(CommentStarTemplateHistoryQuery query) {
        CommentStarTemplateDO starTemplate = commentStarTemplateDAO.selectById(query.getStarTemplateId());
        if (starTemplate == null) {
            throw new ApplicationException(ResultEnum.STAR_TEMPLATE_NOT_EXIST);
        }
        if (query.isPage()) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<CommentStarTemplateHistoryDO> historyList = historyDAO.selectByStarTemplateIds(Collections.singleton(starTemplate.getId()));
        List<CommentStarTemplateHistoryDTO> histories = historyList.stream().map(x -> {
            CommentStarTemplateHistoryDTO dto = new CommentStarTemplateHistoryDTO();
            dto.setAppId(x.getAppId());
            dto.setStarTemplateId(x.getStarTemplateId());
            dto.setStarTemplateName(x.getStarTemplateName());
            dto.setUpdateTime(x.getCreatedTime());
            //解决json乱码问题
            String finalStr = new String(x.getHistory().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
            CommentStarTemplateDTO history = JSONObject.parseObject(finalStr, CommentStarTemplateDTO.class);
            dto.setHistory(history);
            return dto;
        }).collect(Collectors.toList());
        return histories;
    }

    @Transactional
    @Override
    public Boolean delete(Set<Long> ids) {
        List<CommentStarTemplateDetailDO> detailList = commentStarTemplateDetailDAO.selectByStarTemplateIds(ids);
        Set<Long> detailIds = detailList.stream().map(CommentStarTemplateDetailDO::getId).collect(Collectors.toSet());
        List<Long> businessIds =commentStarTemplateDAO.selectBusinessIds(Lists.newArrayList(ids));
        commentStarTemplateDAO.removeByIds(ids);
        historyDAO.deleteByStarTemplateIds(ids);
        if (CollectionUtils.isNotEmpty(detailIds)) {
            commentStarTemplateDetailDAO.removeByIds(detailIds);
            commentStarTemplateDetailLabelConnectDAO.removeByStarTemplateDetailIds(detailIds);
        }
        RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY,businessIds);
        return true;
    }

    @Override
    public CommentStarTemplateDTO getStarTemplate(Long id) {
        CommentStarTemplateDO starTemplate = commentStarTemplateDAO.selectById(id);
        if (starTemplate == null) {
            throw new ApplicationException(ResultEnum.STAR_TEMPLATE_NOT_EXIST);
        }
        //详情
        List<CommentStarTemplateDetailDO> detailList = commentStarTemplateDetailDAO.selectByStarTemplateIds(Collections.singleton(starTemplate.getId()));

        Set<Long> detailIds = detailList.stream()
                .filter(Objects::nonNull)
                .map(CommentStarTemplateDetailDO::getId)
                .collect(Collectors.toSet());

        List<CommentStarTemplateDetailLabelConnectDO> connectList = commentStarTemplateDetailLabelConnectDAO.selectByStarTemplateDetailIds(detailIds);
        Map<Long, List<CommentStarTemplateDetailLabelConnectDO>> connectMap = connectList.stream()
                .collect(Collectors.groupingBy(CommentStarTemplateDetailLabelConnectDO::getStarTemplateDetailId));

        //详情关联标签
        Set<Long> labelTemplateIds = connectList.stream().filter(Objects::nonNull).map(CommentStarTemplateDetailLabelConnectDO::getLabelTemplateId).collect(Collectors.toSet());
        List<CommentLabelTemplateDO> labelTemplates = connectList.isEmpty() ? new ArrayList<>() :
                (ArrayList) commentLabelTemplateDao.listByIds(labelTemplateIds);
        Map<Long, CommentLabelTemplateDO> labelMap = labelTemplates.stream().collect(Collectors.toMap(CommentLabelTemplateDO::getId, x -> x));

        List<CommentStarTemplateDetailDTO> details = detailList.stream().map(x -> {
            CommentStarTemplateDetailDTO detailDTO = x.clone(CommentStarTemplateDetailDTO.class);
            detailDTO.setSupportSettings(JSONObject.parseObject(x.getSupportSettings(), CommentStarTemplateDetailSupportSettingsDTO.class));
            Set<Long> labelIds = connectMap.getOrDefault(x.getId(), new ArrayList<>()).stream()
                    .map(CommentStarTemplateDetailLabelConnectDO::getLabelTemplateId)
                    .collect(Collectors.toSet());
            //每个等级项对应的标签列表
            List<CommentLabelTemplateDTO> labelTemplateDTOS = labelIds.stream().map(z -> {
                CommentLabelTemplateDO labelTemplateDO = labelMap.get(z);
                return labelTemplateDO == null ? null : labelTemplateDO.clone(CommentLabelTemplateDTO.class);
            }).filter(Objects::nonNull).collect(Collectors.toList());
            detailDTO.setLabels(labelTemplateDTOS);
            return detailDTO;
        }).collect(Collectors.toList());

        CommentStarTemplateDTO dto = starTemplate.clone(CommentStarTemplateDTO.class);
        dto.setDetails(details);
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveStarDetail(CommentStarTemplateDetailDTO dto) {

        this.saveStarHistory(dto.getStarTemplateId());
        log.info("开始保存星级评价明细：dto:{}", dto);
        CommentStarTemplateDetailDO templateDetail = dto.clone(CommentStarTemplateDetailDO.class);
        templateDetail.setSupportSettings(JSONObject.toJSONString(dto.getSupportSettings()));
        return commentStarTemplateDetailDAO.save(templateDetail);
    }

    @Override
    public Boolean updateStarDetail(Long id, CommentStarTemplateDetailDTO dto) {
        // 保存历史记录
        this.saveStarHistory(dto.getStarTemplateId());

        log.info("开始修改星级评价明细：dto:{}", dto);
        CommentStarTemplateDetailDO templateDetail = dto.clone(CommentStarTemplateDetailDO.class);
        templateDetail.setId(id);
        templateDetail.setSupportSettings(JSONObject.toJSONString(dto.getSupportSettings()));
        return commentStarTemplateDetailDAO.updateById(templateDetail);
    }

    @Override
    public Boolean saveStarHistory(Long id) {

        CommentStarTemplateDTO dto = this.getStarTemplate(id);
        CommentStarTemplateHistoryDO historyDO = CommentStarTemplateHistoryDO.builder().build();
        historyDO.setAppId(dto.getAppId());
        historyDO.setHistory(JSONObject.toJSONString(dto));
        historyDO.setStarTemplateId(id);
        historyDO.setStarTemplateName(dto.getName());
        log.info("开始保存星级评价历史记录，history:{}", historyDO);
        return historyDAO.save(historyDO);
    }

    @Override
    public Boolean deleteStarDetail(Long starTemplateId, Long id) {
        this.saveStarHistory(starTemplateId);
        log.info("开始删除星级评价明细，id:{}", id);
        commentStarTemplateDetailDAO.removeById(id);

        log.info("开始删除星级评价明细关联标签...");
        CommentStarTemplateDetailLabelConnectDO connectDO = CommentStarTemplateDetailLabelConnectDO.builder().build();
        connectDO.setStarTemplateDetailId(id);
        UpdateWrapper<CommentStarTemplateDetailLabelConnectDO> wrapper = new UpdateWrapper<>(connectDO);
        return commentStarTemplateDetailLabelConnectDAO.remove(wrapper);
    }

}
