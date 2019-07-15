package com.deepexi.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.dao.*;
import com.deepexi.promotion.designpatterns.template.AbstractStatisticTemplate;
import com.deepexi.promotion.designpatterns.template.StatisticTemplateFactory;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.*;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.promotion.mapper.CommentInfoMapper;
import com.deepexi.promotion.service.CommentDataService;
import com.deepexi.promotion.service.CommentInfoService;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.BeanCopierUtils;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Service
@Slf4j
public class CommentInfoServiceImpl
        extends ServiceImpl<CommentInfoMapper, CommentInfoDO> implements CommentInfoService {

    @Resource
    private CommentInfoDAO commentInfoDAO;

    @Resource
    private CommentLabelDAO commentLabelDAO;

    @Resource
    private CommentStarDAO commentStarDAO;

    @Resource
    private CommentResourceDAO commentResourceDAO;

    @Resource
    private CommentDetailDAO commentDetailDAO;

    @Resource
    private ExecutorService executorService;

    @Resource
    private AppRuntimeEnv appRuntimeEnv;

    @Resource
    private StatisticTemplateFactory statisticTemplateFactory;

    @Resource
    private CommentDataService commentDataService;

    @Override
    public List<CommentInfoQueryDTO> listCommentInfoQueryDTO(CommentInfoQuery query) {
        //支持appId IN 查询
        if (CollectionUtil.isEmpty(query.getAppIds())) {
            query.setAppIds(new HashSet<>());
        }
        if (query.getAppId() != null) {
            query.getAppIds().add(query.getAppId());
        }
        //支持targetId IN 查询
        if (CollectionUtil.isEmpty(query.getTargetIds())) {
            query.setTargetIds(CollectionUtil.createArrayList());
        }
        if (query.getTargetId() != null) {
            query.getTargetIds().add(query.getTargetId());
        }
        List<CommentDetailDO> commentDetailDOPage = commentInfoDAO.listByCommentInfoQuery(query);
        if (CollectionUtil.isEmpty(commentDetailDOPage)) {
            return CollectionUtil.createArrayList();
        }
        List<CommentInfoQueryDTO> commentInfoQueryDTOS
                = ObjectCloneUtils.convertList(commentDetailDOPage, CommentInfoQueryDTO.class, CloneDirection.OPPOSITE);
        this.buildCommentInfoQueryDTO(query.getTenantId(), commentDetailDOPage, commentInfoQueryDTOS);
        return commentInfoQueryDTOS;
    }

    @Override
    public CommentDetailResultDTO getCommentDetailWithTree(CommentInfoQuery query) {
        CommentDetailDO commentDetailDO = Optional.ofNullable(commentDetailDAO.getById(query.getCommentDetailId()))
                .orElseThrow(() -> new ApplicationException(ResultEnum.RECORD_NOT_FOUND));
        if (commentDetailDO.getParentId() != null ||
                !CommentTypeEnum.COMMENT.getCode().equals(commentDetailDO.getCommentType())) {
            log.error("此条记录不是父评论(不提供查询功能),ID={}", commentDetailDO.getId());
            throw new ApplicationException(ResultEnum.RECORD_NOT_FOUND);
        }
        // 查询评价详情的回复和追评
        List<CommentDetailDO> replyAndReview = commentDetailDAO.selectByParentId(query);
        List<CommentDetailDO> allList = CommentDetailDO.treeListToList(replyAndReview);
        allList.add(commentDetailDO);
        List<Long> detailIdList = allList.stream().map(CommentDetailDO::getId).collect(Collectors.toList());

        List<CommentDetailResultDTO> detailResultDTOS
                = ObjectCloneUtils.convertList(allList, CommentDetailResultDTO.class, CloneDirection.OPPOSITE);

        // 查询星评
        List<CommentStarDO> commentStarDOS = commentStarDAO.listByCommentDetailIdList(detailIdList);
        List<Long> starIdList = commentStarDOS.stream().map(CommentStarDO::getId).collect(Collectors.toList());
        List<CommentStarResultDTO> starDTOList = commentStarDOS.stream()
                .map(item -> item.clone(CommentStarResultDTO.class)).collect(Collectors.toList());

        // 查询资源
        List<CommentResourceDO> commentResourceDOList = commentResourceDAO
                .listByRelevanceIdList(detailIdList, starIdList);
        Map<String, String> commentResourceMap = commentResourceDOList.stream().collect(Collectors.toMap(
                key -> concatWithBar(key.getRelevanceType(), key.getRelevanceId(), key.getResourceType()),
                CommentResourceDO::getResourceContent, (oldResource, newResource) -> newResource));

        // 查询标签
        List<CommentLabelDO> commentLabelDOList = commentLabelDAO.listByRelevanceIdList(
                detailIdList, starIdList);
        Map<String, List<CommentLabelDTO>> commentLabelMap = commentLabelDOList.stream()
                .map(item -> item.clone(CommentLabelDTO.class))
                .collect(Collectors.groupingBy(item -> concatWithBar(item.getRelevanceType(), item.getRelevanceId())));

        // 设置评论明细的资源和标签
        detailResultDTOS.forEach(item -> {
            item.setCommentResource(commentResourceMap, CommentRelevanceTypeEnum.COMMENT_DETAIL);
            List<CommentLabelDTO> labelList = commentLabelMap.get(
                    concatWithBar(CommentRelevanceTypeEnum.COMMENT_DETAIL.getCode(), item.getId()));
            item.setCommentLabelList(CollectionUtil.isNotEmpty(labelList) ?
                    ObjectCloneUtils.convertList(labelList, CommentLabelInputDTO.class) : null);
        });

        // 设置星评的资源和标签
        starDTOList.forEach(item -> {
            item.setCommentResource(commentResourceMap, CommentRelevanceTypeEnum.COMMENT_STAR);
            List<CommentLabelDTO> labelList = commentLabelMap.get(
                    concatWithBar(CommentRelevanceTypeEnum.COMMENT_STAR.getCode(), item.getId()));
            item.setCommentLabelList(CollectionUtil.isNotEmpty(labelList) ?
                    ObjectCloneUtils.convertList(labelList, CommentLabelInputDTO.class) : null);
        });

        // 根据 detailId 分组
        Map<Long, List<CommentStarResultDTO>> starMap = starDTOList.stream().collect(
                Collectors.groupingBy(CommentStarResultDTO::getCommentDetailId));

        // 设置评论详情的星评资源
        detailResultDTOS.forEach(item -> item.setCommentStarList(starMap.get(item.getId())));

        return buildTree(detailResultDTOS).get(0);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean createComment(CommentInfoInputDTO input) {
        log.info("发布评价, 内容：{}", input);
        CommentInfoDO comment = new CommentInfoDO();
        BeanCopierUtils.copyProperties(input, comment);
        commentInfoDAO.save(comment);

        List<CommentDetailInputDTO> detailList = input.getCommentDetailList();
        List<CommentResourceDO> commentResourceList = new ArrayList<>();
        List<CommentLabelDO> commentLabelList = new ArrayList<>();
        List<CommentStatisticDO> commentStatisticList;
        List<CommentDetailDO> commentDetailList = new ArrayList<>();
        List<CommentStarDO> commentStarList = new ArrayList<>();
        AtomicLong insertSequence = new AtomicLong(0L);
        for (CommentDetailInputDTO detail : detailList) {
            insertSequence.incrementAndGet();
            CommentDetailDO commentDetail = CommentDetailDO.builder()
                    .appId(input.getAppId()).modelId(detail.getModelId()).modelName(detail.getModelName())
                    .commentId(comment.getId()).commentType(CommentTypeEnum.COMMENT.getCode())
                    .userId(input.getUserId()).targetId(detail.getTargetId()).checkStatus(input.getCheckStatus())
                    .channel(input.getChannel()).appBusinessId(input.getAppBusinessId()).build();
            // 业务关联
            commentDetail.setInsertSequence(insertSequence.get());
            commentDetailList.add(commentDetail);
            // 构造关联的数据
            commentDataService.associatedData(insertSequence, detail.getCommentResourceList(), detail.getCommentLabelList(),
                    detail.getCommentStarList(), commentDetail, commentResourceList, commentLabelList, commentStarList);
        }
        AbstractStatisticTemplate statisticTemplate = statisticTemplateFactory.getInstance(CommentTypeEnum.COMMENT);
        // 构建统计记录
        List<CommentStatisticDTO> commentStatisticDTOS = buildCommentStatisticDTO(input, statisticTemplate);
        commentStatisticList = ObjectCloneUtils.convertList(commentStatisticDTOS, CommentStatisticDO.class);
        // 数据落库
        commentDataService.insertData(commentDetailList, commentResourceList, commentLabelList,
                commentStarList, commentStatisticList);
        // 删除缓存
        this.clearCache(commentDetailList);
        return Boolean.TRUE;
    }

    @Override
    public List<CommentStarDTO> listCommentStarDTO(Long commentDetailId) {

        List<CommentStarDO> commentStarDOList = commentStarDAO.listByCommentDetailId(commentDetailId);
        List<Long> commentStarList =
                commentStarDOList.stream().map(CommentStarDO::getId).collect(Collectors.toList());
        List<CommentResourceDO> commentResourceDOList = commentResourceDAO
                .listByRelevanceIdList(commentStarList, CommentRelevanceTypeEnum.COMMENT_STAR);
        Map<String, String> resourceMap = commentResourceDOList.stream()
                .collect(Collectors.toMap(
                        key -> concatWithBar(key.getRelevanceId(), key.getResourceType()),
                        CommentResourceDO::getResourceContent));

        List<CommentLabelDO> commentLabelDOList =
                commentLabelDAO.listByRelevanceIdList(commentStarList, CommentRelevanceTypeEnum.COMMENT_STAR);
        Map<Long, List<CommentLabelDTO>> labelMap = commentLabelDOList.stream()
                .map(item -> item.clone(CommentLabelDTO.class))
                .collect(Collectors.groupingBy(CommentLabelDTO::getRelevanceId));

        List<CommentStarDTO> commentStarDTOS =
                ObjectCloneUtils.convertList(commentStarDOList, CommentStarDTO.class, CloneDirection.OPPOSITE);
        commentStarDTOS.forEach(starDTO -> {
            starDTO.setCommentLabelList(labelMap.get(starDTO.getId()));
            starDTO.setCommentVideo(resourceMap.get(
                    concatWithBar(starDTO.getId(), CommentResourceTypeEnum.VIDEO.getCode())));
            starDTO.setCommentImage(resourceMap.get(
                    concatWithBar(starDTO.getId(), CommentResourceTypeEnum.IMAGE.getCode())));
            starDTO.setCommentText(resourceMap.get(
                    concatWithBar(starDTO.getId(), CommentResourceTypeEnum.TEXT.getCode())));
        });
        return commentStarDTOS;
    }

    /**
     * 删除评价
     *
     * @param commentDetailId 评价明细id
     * @return true:成功，false:失败
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteComment(Long commentDetailId) {
        // 需删除的当前评价
        CommentDetailDO commentDetailDo = Optional.ofNullable(commentDetailDAO.getById(commentDetailId))
                .orElseThrow(() -> new ApplicationException(ResultEnum.RECORD_NOT_FOUND));
        if (CommentTypeEnum.ADDITIONAL_COMMENT.equals(commentDetailDo.getCommentType())) {
            throw new ApplicationException(ResultEnum.ADDITIONAL_COMMENT_NOT_ALLOW_DELETE);
        }
        boolean isNeedToReduceReply = false;
        // 查询父评论 看看是否需要更新父评论的回复状态
        if (commentDetailDo.getParentId() != null) {
            // 查找父评论下是否有超过2条回复记录
            CommentDetailDO parent = Optional.ofNullable(commentDetailDAO.getById(commentDetailDo.getParentId()))
                    .orElseThrow(() -> new ApplicationException(ResultEnum.RECORD_NOT_FOUND));
            if (CommentTypeEnum.COMMENT.getCode().equals(parent.getCommentType())) {
                int limit = 2;
                CommentDetailDO clone = commentDetailDo.clone(CommentDetailDO.class);
                clone.setCommentType(CommentTypeEnum.REPLY_COMMENT.getCode());
                List<CommentDetailDO> replyDetail = commentDetailDAO.listByParentIdAndCommentTypeLimit(commentDetailDo, limit);
                if (CollectionUtil.isNotEmpty(replyDetail) && replyDetail.size() < limit) {
                    //更新父评论 回复状态为没有回复
                    parent.setUpdatedTime(null);
                    parent.setReplyStatus(CommentReplyStatusEnum.NO_REPLY.getStatus());
                    commentDetailDAO.updateById(parent);
                    isNeedToReduceReply = true;
                }
            }
        }
        // 查询评价详情的回复和追评
        List<CommentDetailDO> replyAndReview = commentDetailDAO.selectByParentId(CommentInfoQuery.builder()
                .commentDetailId(commentDetailId).build());
        List<CommentDetailDO> allList = CommentDetailDO.treeListToList(replyAndReview);
        allList.add(commentDetailDo);
        // 构造统计参数，用于查出统计记录，删除评论时，更新统计记录。
        List<CommentStatisticParamDTO> statisticParamDtoList
                = ObjectCloneUtils.convertList(allList, CommentStatisticParamDTO.class);

        List<Long> detailIdList = allList.stream().map(CommentDetailDO::getId).collect(Collectors.toList());
        // 查询星评
        List<CommentStarDO> commentStarDoList = commentStarDAO.listByCommentDetailIdList(detailIdList);
        List<Long> starIdList = commentStarDoList.stream().map(CommentStarDO::getId).collect(Collectors.toList());

        // 查询资源，标签
        List<CommentResourceDO> commentResourceDoList = commentResourceDAO.listByRelevanceIdList(detailIdList, starIdList);
        List<CommentLabelDO> commentLabelDoList = commentLabelDAO.listByRelevanceIdList(detailIdList, starIdList);

        Map<String, List<CommentResourceDO>> commentResourceMap = commentResourceDoList.stream()
                .collect(Collectors.groupingBy(item -> concatWithBar(item.getRelevanceType(), item.getRelevanceId())));
        Map<String, List<CommentLabelDO>> commentLabelMap = commentLabelDoList.stream()
                .collect(Collectors.groupingBy(item -> concatWithBar(item.getRelevanceType(), item.getRelevanceId())));

        // 设置评论明细的资源和标签
        statisticParamDtoList.forEach(item -> {
            String key = concatWithBar(CommentRelevanceTypeEnum.COMMENT_DETAIL.getCode(), item.getId());
            item.setCommentResourceList(ObjectCloneUtils.convertList(
                    commentResourceMap.get(key), CommentResourceInputDTO.class));
            item.setCommentLabelList(ObjectCloneUtils.convertList(
                    commentLabelMap.get(key), CommentLabelInputDTO.class));
        });
        // 设置星评的资源和标签
        List<CommentStarInputDTO> starInputDtoList = ObjectCloneUtils.convertList(commentStarDoList, CommentStarInputDTO.class);
        starInputDtoList.forEach(item -> {
            String key = concatWithBar(CommentRelevanceTypeEnum.COMMENT_STAR.getCode(), item.getId());
            item.setCommentResourceList(ObjectCloneUtils.convertList(
                    commentResourceMap.get(key), CommentResourceInputDTO.class));
            item.setCommentLabelList(ObjectCloneUtils.convertList(
                    commentLabelMap.get(key), CommentLabelInputDTO.class));
        });
        // 根据 detailId 分组
        Map<Long, List<CommentStarInputDTO>> starInputMap = starInputDtoList.stream().collect(
                Collectors.groupingBy(CommentStarInputDTO::getCommentDetailId));
        // 设置评论详情的星评资源
        statisticParamDtoList.forEach(item -> item.setCommentStarList(starInputMap.get(item.getId())));

        //获取(发布、追评、回复)统计记录
        List<CommentStatisticDTO> commentStatisticDtoList = Lists.newArrayList();
        for (CommentStatisticParamDTO statisticParamDTO : statisticParamDtoList) {
            AbstractStatisticTemplate statisticTemplate =
                    statisticTemplateFactory.getInstance(CommentTypeEnum.getByCode(statisticParamDTO.getCommentType()));
            commentStatisticDtoList.addAll(statisticTemplate.count(Lists.newArrayList(statisticParamDTO)));
        }

        for (CommentStatisticDTO commentStatisticDTO : commentStatisticDtoList) {
            commentStatisticDTO.setId(null);
        }
        // 统计去重
        commentStatisticDtoList = Lists.newArrayList(Sets.newHashSet(commentStatisticDtoList));
        // 针对不是顶级父评论的情况(二级评论才会影响顶级评论)
        if (!isNeedToReduceReply) {
            // 不需要减回复记录
            commentStatisticDtoList.removeIf(
                    (item) -> CommentStatisticTypeEnum.REPLY_COMMENT.getCode().equals(item.getType()));
        }
        List<CommentStatisticDO> commentStatisticDoList = ObjectCloneUtils.convertList(
                commentStatisticDtoList, CommentStatisticDO.class);
        //删除数据,更新统计记录
        commentDataService.removeData(allList, commentStarDoList, commentResourceDoList,
                commentLabelDoList, commentStatisticDoList);
        // 删除缓存
        String key = CommentConstants.COMMENT_INFO_KEY + commentDetailDo.getAppId() + ":" +
                commentDetailDo.getAppBusinessId() + ":" + commentDetailDo.getModelId() + ":" +
                commentDetailDo.getTargetId();
        RedisUtils.deleteCache(key);
        return Boolean.TRUE;
    }

    @Override
    public Long commentCount(CommentCountQuery commentCountQuery) {
        return commentInfoDAO.countComment(commentCountQuery);
    }

    @Override
    public List<CommentInfoQueryDTO> listCommentByStatisticType(CommentStatisticTypeQuery statisticTypeQuery) {
        final String tenantId = appRuntimeEnv.getTenantId();
        statisticTypeQuery.setTenantId(tenantId);
        List<CommentDetailDO> commentDetailDOPage = commentInfoDAO.listCommentByStatisticType(statisticTypeQuery);
        if (CollectionUtil.isEmpty(commentDetailDOPage)) {
            return CollectionUtil.createArrayList();
        }
        List<CommentInfoQueryDTO> commentInfoQueryDTOS
                = ObjectCloneUtils.convertList(commentDetailDOPage, CommentInfoQueryDTO.class, CloneDirection.OPPOSITE);
        this.buildCommentInfoQueryDTO(tenantId, commentDetailDOPage, commentInfoQueryDTOS);
        return commentInfoQueryDTOS;
    }

    /**
     * 根据评论明细列表 查询组装评论内容
     *
     * @param tenantId             租户ID
     * @param commentDetailDOList  评论明细列表
     * @param commentInfoQueryDTOS 评论内容列表
     */
    private void buildCommentInfoQueryDTO(String tenantId, List<CommentDetailDO> commentDetailDOList,
                                          List<CommentInfoQueryDTO> commentInfoQueryDTOS) {
        List<Long> commentDetailList =
                commentDetailDOList.stream().map(CommentDetailDO::getId).collect(Collectors.toList());

        // 查询评价资源 直接挂在评价明细下的
        Future<Map<String, String>> futureResource = executorService.submit(() -> {
            appRuntimeEnv.setTenantId(tenantId);
            List<CommentResourceDO> commentResourceDOList = commentResourceDAO
                    .listByRelevanceIdList(commentDetailList, CommentRelevanceTypeEnum.COMMENT_DETAIL);
            return commentResourceDOList.stream()
                    .collect(Collectors.toMap(
                            key -> concatWithBar(key.getRelevanceId(), key.getResourceType()),
                            CommentResourceDO::getResourceContent, (oldResource, newResource) -> newResource));
        });

        // 查询星评评价
        Future<Map<Long, List<CommentStarInputDTO>>> futureStar = executorService.submit(() -> {
            appRuntimeEnv.setTenantId(tenantId);
            List<CommentStarDO> commentStarDOList = commentStarDAO.listByCommentDetailIdList(commentDetailList);
            return commentStarDOList.stream()
                    .map(item -> item.clone(CommentStarInputDTO.class))
                    .collect(Collectors.groupingBy(CommentStarInputDTO::getCommentDetailId));
        });

        try {
            Map<String, String> resourceMap = futureResource.get();
            Map<Long, List<CommentStarInputDTO>> starMap = futureStar.get();
            commentInfoQueryDTOS.forEach(item -> {
                item.setCommentResource(resourceMap);
                item.setCommentStarList(starMap.get(item.getId()));
            });
        } catch (InterruptedException | ExecutionException e) {
            log.error("线程池执行任务异常：", e);
            throw new ApplicationException(ResultEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 构造树形结构(递归)
     *
     * @param treeNodes list
     * @return 返回树形结构
     */
    private List<CommentDetailResultDTO> buildTree(List<CommentDetailResultDTO> treeNodes) {
        List<CommentDetailResultDTO> trees = new ArrayList<>();
        for (CommentDetailResultDTO treeNode : treeNodes) {
            if (null == treeNode.getParentId()) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    private CommentDetailResultDTO findChildren(CommentDetailResultDTO treeNode, List<CommentDetailResultDTO> treeNodes) {
        for (CommentDetailResultDTO it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getReplayList() == null) {
                    treeNode.setReplayList(new ArrayList<>());
                }
                treeNode.getReplayList().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }


    /**
     * 构建key
     *
     * @param objects 拼接的对象
     * @return first-second
     */
    private String concatWithBar(Object... objects) {
        return StringUtils.joinWith("-", objects);
    }

    /**
     * 构建统计记录
     *
     * @param input CommentInfoInputDTO
     * @return Set<CommentStatisticDTO>
     */
    private List<CommentStatisticDTO> buildCommentStatisticDTO(CommentInfoInputDTO input, AbstractStatisticTemplate statisticTemplate) {
        List<CommentStatisticParamDTO> statisticParamDTOList = CollectionUtil.createArrayList();

        input.getCommentDetailList().forEach((commentDetailInputDTO -> {
            CommentStatisticParamDTO statisticParamDTO = input.clone(CommentStatisticParamDTO.class);
            statisticParamDTO.setModelId(commentDetailInputDTO.getModelId());
            statisticParamDTO.setTargetId(commentDetailInputDTO.getTargetId());
            if (CollectionUtil.isNotEmpty(commentDetailInputDTO.getCommentStarList())) {
                statisticParamDTO.setCommentStarList(commentDetailInputDTO.getCommentStarList());
            }
            if (CollectionUtil.isNotEmpty(commentDetailInputDTO.getCommentResourceList())) {
                statisticParamDTO.setCommentResourceList(commentDetailInputDTO.getCommentResourceList());
            }
            if (CollectionUtil.isNotEmpty(commentDetailInputDTO.getCommentLabelList())) {
                statisticParamDTO.setCommentLabelList(commentDetailInputDTO.getCommentLabelList());
            }
            statisticParamDTOList.add(statisticParamDTO);
        }));
        return statisticTemplate.count(statisticParamDTOList);
    }

    /**
     * 发布评价后清除缓存
     *
     * @param commentDetailList 根据评论明细构造key
     */
    private void clearCache(List<CommentDetailDO> commentDetailList) {
        HashSet<String> keySet = Sets.newHashSet();
        for (CommentDetailDO commentDetailDO : commentDetailList) {
            String key = CommentConstants.COMMENT_INFO_KEY + commentDetailDO.getAppId() + ":" +
                    commentDetailDO.getAppBusinessId() + ":" + commentDetailDO.getModelId() + ":" +
                    commentDetailDO.getTargetId();
            keySet.add(key);
        }
        RedisUtils.deleteCache(Lists.newArrayList(keySet));
    }

}