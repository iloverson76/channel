package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.deepexi.promotion.dao.*;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentRelevanceTypeEnum;
import com.deepexi.promotion.enums.CommentTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.CommentDataService;
import com.deepexi.promotion.service.CommentStarTemplateDetailService;
import com.deepexi.promotion.service.CommentStarTemplateService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 此类用于保存评论的数据
 *
 * @author liaoxiaoxin
 * @date 2019/6/20 20:15
 */
@Component
@Slf4j
public class CommentDataServiceImpl implements CommentDataService {

    @Resource
    private CommentLabelDAO commentLabelDAO;

    @Resource
    private CommentStarDAO commentStarDAO;

    @Resource
    private CommentResourceDAO commentResourceDAO;

    @Resource
    private CommentDetailDAO commentDetailDAO;

    @Resource
    private CommentDetailCheckDAO detailCheckDAO;

    @Resource
    private CommentStatisticDAO commentStatisticDAO;

    @Resource
    private ExecutorService executorService;

    @Resource
    private CommentStarTemplateService starTemplateService;

    @Resource
    private CommentStarTemplateDetailService starTemplateDetailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertData(List<CommentDetailDO> commentDetailList, List<CommentResourceDO> commentResourceList,
                           List<CommentLabelDO> commentLabelList, List<CommentStarDO> commentStarList,
                           List<CommentStatisticDO> commentStatisticList) {
        log.info("构建后的标签列表：{}\n, 资源列表：{}\n，星评：{}\n统计：{}",
                commentLabelList, commentResourceList, commentStarList, commentStatisticList);
        // 保存评价明细
        commentDetailDAO.saveBatch(commentDetailList);
        Map<Long, Long> insertSequenceIdMap = commentDetailList.stream()
                .collect(Collectors.toMap(CommentDetailDO::getInsertSequence, CommentDetailDO::getId));
        commentStarList.forEach(
                item -> item.setCommentDetailId(insertSequenceIdMap.get(item.getCommentDetailId())));
        // 保存星评评价
        commentStarDAO.saveBatch(commentStarList);
        commentStarList.forEach(item -> insertSequenceIdMap.put(item.getInsertSequence(), item.getId()));
        commentLabelList.forEach(
                item -> item.setRelevanceId(insertSequenceIdMap.get(item.getInsertSequence())));
        // 保存评价标签
        commentLabelDAO.saveBatch(commentLabelList);
        commentResourceList.forEach(
                item -> item.setRelevanceId(insertSequenceIdMap.get(item.getInsertSequence())));
        // 保存评价资源
        commentResourceDAO.saveBatch(commentResourceList);
        // 插入评价统计 异步
        if(CollectionUtil.isNotEmpty(commentStatisticList)){
            executorService.execute(
                    () -> commentStatisticDAO.batchInsertOrUpdate(commentStatisticList));
        }
    }

    @Override
    public void associatedData(AtomicLong insertSequence, List<CommentResourceInputDTO> resourceInputDTOS,
                               List<CommentLabelInputDTO> labelInputDTOS, List<CommentStarInputDTO> starInputDTOS,
                               CommentDetailDO commentDetailDO, List<CommentResourceDO> commentResourceList,
                               List<CommentLabelDO> commentLabelList, List<CommentStarDO> commentStarList) {
        long commentDetailInsertSequence = insertSequence.get();
        // 直接挂在评价明细上的评价资源
        buildCommentResource(resourceInputDTOS, commentDetailDO, commentDetailInsertSequence,
                CommentRelevanceTypeEnum.COMMENT_DETAIL, commentResourceList);
        // 直接挂在评价明细上的评价标签
        buildCommentLabel(labelInputDTOS, commentDetailDO.getAppId(), commentDetailInsertSequence,
                CommentRelevanceTypeEnum.COMMENT_DETAIL, commentLabelList);
        // 评价星评表
        if (CollectionUtil.isNotEmpty(starInputDTOS)) {
            for (CommentStarInputDTO starInput : starInputDTOS) {
                CommentStarDO commentStar = CommentStarDO.builder()
                        .appId(commentDetailDO.getAppId())
                        // 这里做业务关联，等批量插入后得到id后再转化为id
                        .commentDetailId(commentDetailInsertSequence)
                        .starName(starInput.getStarName()).starTemplateId(starInput.getStarTemplateId())
                        .starTemplateDetailId(starInput.getStarTemplateDetailId()).starValue(starInput.getStarValue())
                        .build();
                commentStar.setInsertSequence(insertSequence.incrementAndGet());
                commentStarList.add(commentStar);
                // 挂在星评上的评价资源
                if (CollectionUtil.isNotEmpty(starInput.getCommentResourceList())) {
                    buildCommentResource(starInput.getCommentResourceList(), commentDetailDO, insertSequence.get(),
                            CommentRelevanceTypeEnum.COMMENT_STAR, commentResourceList);
                }
                // 挂在星评上的评价标签
                if (CollectionUtil.isNotEmpty(starInput.getCommentLabelList())) {
                    buildCommentLabel(starInput.getCommentLabelList(), commentDetailDO.getAppId(), insertSequence.get(),
                            CommentRelevanceTypeEnum.COMMENT_STAR, commentLabelList);
                }
            }
        }
    }

    @Override
    public void queryStarInfoSetToPublishData(CommentInfoInputDTO input) throws ApplicationException {

        // 如果存在星评,取出所有的starTemplateDetailId,用于查询星评ID、星评名称、星评别名、星评值等信息
        Set<Long> starTemplateDetailIdList = input.getCommentDetailList().stream()
                .filter(commentDetailInputDTO -> CollectionUtil.isNotEmpty(commentDetailInputDTO.getCommentStarList()))
                .flatMap(commentDetailInputDTO -> commentDetailInputDTO.getCommentStarList().stream())
                .map(CommentStarInputDTO::getStarTemplateDetailId)
                .collect(Collectors.toSet());
        if (starTemplateDetailIdList.size() > 0) {
            List<CommentStarTemplateDetailDTO> starTemplateDetailDTOS =
                    starTemplateDetailService.queryByIds(new ArrayList<>(starTemplateDetailIdList));
            // 有一些starTemplateDetailId 记录不存在
            if (starTemplateDetailIdList.size() > starTemplateDetailDTOS.size()) {
                throw new ApplicationException(ResultEnum.STAR_TEMPLATE_DETAIL_NOT_FOUND);
            }
            Map<Long, CommentStarTemplateDetailDTO> detailDTOMap = starTemplateDetailDTOS.stream().collect(Collectors.toMap(
                    CommentStarTemplateDetailDTO::getId, starTemplateDetailDTO -> starTemplateDetailDTO));
            // 设置星评ID、星评别名、星评值
            input.getCommentDetailList().forEach(commentDetailInputVO -> {
                if (CollectionUtil.isNotEmpty(commentDetailInputVO.getCommentStarList())) {
                    commentDetailInputVO.getCommentStarList().forEach(commentStarInputVO -> {
                        CommentStarTemplateDetailDTO detailDTO = detailDTOMap.get(commentStarInputVO.getStarTemplateDetailId());
                        commentStarInputVO.setStarTemplateId(detailDTO.getStarTemplateId());
                        commentStarInputVO.setStarAlias(detailDTO.getNickName());
                        commentStarInputVO.setStarValue(detailDTO.getValue());
                    });
                }
            });
            // 查询星评名称
            Set<Long> starTemplateIdList = input.getCommentDetailList().stream()
                    .filter(commentDetailInputDTO -> CollectionUtil.isNotEmpty(commentDetailInputDTO.getCommentStarList()))
                    .flatMap(commentDetailInputVO -> commentDetailInputVO.getCommentStarList().stream())
                    .map(CommentStarInputDTO::getStarTemplateId).collect(Collectors.toSet());
            Collection<CommentStarTemplateDO> starTemplateDOS = starTemplateService.listByIds(starTemplateIdList);
            Map<Long, CommentStarTemplateDO> starTemplateDOMap = starTemplateDOS.stream().collect(
                    Collectors.toMap(CommentStarTemplateDO::getId, starTemplate -> starTemplate));
            // 设置星评名称
            input.getCommentDetailList().forEach(commentDetailInputDTO -> {
                if (CollectionUtil.isNotEmpty(commentDetailInputDTO.getCommentStarList())) {
                    commentDetailInputDTO.getCommentStarList().forEach(
                            commentStarInputDTO -> commentStarInputDTO.setStarName(
                                    starTemplateDOMap.get(commentStarInputDTO.getStarTemplateId()).getName())
                    );
                }
            });

        }
    }

    @Override
    public void queryStarInfoSetToReplyOrAdditionalData(CommentDetailReplyInputDTO input) throws ApplicationException {
        if (CollectionUtil.isEmpty(input.getCommentStarList())) {
            return;
        }
        // 如果存在星评,取出所有的starTemplateDetailId,用于查询星评别名
        Set<Long> starTemplateDetailIdSet = input.getCommentStarList().stream()
                .map(CommentStarInputDTO::getStarTemplateDetailId).collect(Collectors.toSet());
        List<CommentStarTemplateDetailDTO> starTemplateDetailDTOS =
                starTemplateDetailService.queryByIds(Lists.newArrayList(starTemplateDetailIdSet));
        // 有starTemplateDetailId 查找不到记录
        if (starTemplateDetailIdSet.size() > starTemplateDetailDTOS.size()) {
            throw new ApplicationException(ResultEnum.STAR_TEMPLATE_DETAIL_NOT_FOUND);
        }
        Map<Long, CommentStarTemplateDetailDTO> detailDTOMap = starTemplateDetailDTOS.stream().collect(Collectors.toMap(
                CommentStarTemplateDetailDTO::getId, starTemplateDetailDTO -> starTemplateDetailDTO));
        // 设置星评ID、星评别名、星评值
        input.getCommentStarList().forEach(commentStarInputDTO -> {
            CommentStarTemplateDetailDTO detailDTO = detailDTOMap.get(commentStarInputDTO.getStarTemplateDetailId());
            commentStarInputDTO.setStarTemplateId(detailDTO.getStarTemplateId());
            commentStarInputDTO.setStarAlias(detailDTO.getNickName());
            commentStarInputDTO.setStarValue(detailDTO.getValue());
        });

        // 查询星评名称
        Set<Long> starTemplateIdList = input.getCommentStarList().stream()
                .map(CommentStarInputDTO::getStarTemplateId).collect(Collectors.toSet());
        Collection<CommentStarTemplateDO> starTemplateDOS = starTemplateService.listByIds(starTemplateIdList);
        Map<Long, CommentStarTemplateDO> starTemplateDOMap = starTemplateDOS.stream().collect(
                Collectors.toMap(CommentStarTemplateDO::getId, starTemplate -> starTemplate));
        // 设置星评名称
        input.getCommentStarList().forEach(commentStarInputVO -> commentStarInputVO.setStarName(
                starTemplateDOMap.get(commentStarInputVO.getStarTemplateId()).getName()));

    }

    @Override
    public CommentStatisticParamDTO getParentStatisticInfo(Long parentId) {
        //
        CommentDetailDO parentDetailDO = commentDetailDAO.getById(parentId);
        CommentStatisticParamDTO statisticParamDTO = parentDetailDO.clone(CommentStatisticParamDTO.class);
        // 获取星评
        List<CommentStarDO> commentStarDOS = commentStarDAO.listByCommentDetailId(parentDetailDO.getId());
        List<CommentResourceDO> parentResourceDOS;
        List<CommentLabelDO> parentLabelDOS;
        List<Long> detailIds = Lists.newArrayList(parentDetailDO.getId());
        // 获取资源，标签
        if (CollectionUtil.isEmpty(commentStarDOS)) {
            statisticParamDTO.setCommentStarList(ObjectCloneUtils.convertList(commentStarDOS, CommentStarInputDTO.class));
            parentResourceDOS = commentResourceDAO.listByRelevanceIdList(detailIds, CommentRelevanceTypeEnum.COMMENT_DETAIL);
            parentLabelDOS = commentLabelDAO.listByRelevanceIdList(detailIds, CommentRelevanceTypeEnum.COMMENT_DETAIL);
        } else {
            List<Long> starIdList = commentStarDOS.stream().map(CommentStarDO::getId).collect(Collectors.toList());
            parentResourceDOS = commentResourceDAO.listByRelevanceIdList(detailIds, starIdList);
            parentLabelDOS = commentLabelDAO.listByRelevanceIdList(detailIds, starIdList);
        }
        if (CollectionUtil.isNotEmpty(parentResourceDOS)) {
            statisticParamDTO.setCommentResourceList(
                    ObjectCloneUtils.convertList(parentResourceDOS, CommentResourceInputDTO.class));
        }
        if (CollectionUtil.isNotEmpty(parentLabelDOS)) {
            statisticParamDTO.setCommentLabelList(
                    ObjectCloneUtils.convertList(parentLabelDOS, CommentLabelInputDTO.class));
        }
        return statisticParamDTO;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeData(List<CommentDetailDO> commentDetailDoList, List<CommentStarDO> commentStarDoList,
                           List<CommentResourceDO> commentResourceDoList, List<CommentLabelDO> commentLabelDoList,
                           List<CommentStatisticDO> statisticDoList) {
        List<Long> detailIdList = commentDetailDoList.stream().map(CommentDetailDO::getId).collect(Collectors.toList());
        commentDetailDAO.removeByIds(detailIdList);
        if (CollectionUtil.isNotEmpty(commentStarDoList)) {
            commentStarDAO.removeByIds(commentStarDoList.stream().map(CommentStarDO::getId).collect(Collectors.toList()));
        }
        if (CollectionUtil.isNotEmpty(commentResourceDoList)) {
            commentResourceDAO.removeByIds(commentResourceDoList.stream()
                    .map(CommentResourceDO::getId).collect(Collectors.toList()));
        }
        if (CollectionUtil.isNotEmpty(commentLabelDoList)) {
            commentLabelDAO.removeByIds(commentLabelDoList.stream()
                    .map(CommentLabelDO::getId).collect(Collectors.toList()));
        }
        // 删除审核表数据
        LambdaUpdateWrapper<CommentDetailCheckDO> wrapper = new UpdateWrapper<CommentDetailCheckDO>().lambda();
        wrapper.in(CommentDetailCheckDO::getCommentDetailId, detailIdList);
        detailCheckDAO.remove(wrapper);
        // 减去对应的统计数量
        executorService.submit(() -> commentStatisticDAO.reduceCountValueByCode(statisticDoList));

    }

    /**
     * 根据CommentResourceInputDTO构建资源
     *
     * @param inputList           输入的资源
     * @param commentDetail       评价明细
     * @param insertSequence      插入序号
     * @param type                资源类型
     * @param commentResourceList 存放构建好的资源集合
     */
    private void buildCommentResource(List<CommentResourceInputDTO> inputList, CommentDetailDO commentDetail,
                                      long insertSequence, CommentRelevanceTypeEnum type,
                                      List<CommentResourceDO> commentResourceList) {
        if (CollectionUtil.isEmpty(inputList)) {
            return;
        }
        for (CommentResourceInputDTO resource : inputList) {
            CommentResourceDO commentResource = CommentResourceDO.builder()
                    .appId(commentDetail.getAppId())
                    .resourceType(resource.getResourceType())
                    .resourceContent(resource.getResourceContent())
                    .relevanceType(type.getCode())
                    .build();
            commentResource.setInsertSequence(insertSequence);
            commentResourceList.add(commentResource);
        }
    }

    /**
     * 根据CommentResourceInputDTO构建标签
     *
     * @param inputList        输入的资源
     * @param appId            应用ID
     * @param insertSequence   插入序号
     * @param type             资源类型
     * @param commentLabelList 存放构建好的资源集合
     */
    private void buildCommentLabel(List<CommentLabelInputDTO> inputList, Long appId, long insertSequence,
                                   CommentRelevanceTypeEnum type, List<CommentLabelDO> commentLabelList) {
        if (CollectionUtil.isEmpty(inputList)) {
            return;
        }
        for (CommentLabelInputDTO label : inputList) {
            CommentLabelDO commentLabel = label.clone(CommentLabelDO.class);
            commentLabel.setAppId(appId);
            commentLabel.setRelevanceType(type.getCode());
            commentLabel.setInsertSequence(insertSequence);
            commentLabelList.add(commentLabel);
        }
    }
}
