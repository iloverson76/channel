package com.deepexi.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentDetailDAO;
import com.deepexi.promotion.designpatterns.template.AbstractStatisticTemplate;
import com.deepexi.promotion.designpatterns.template.StatisticTemplateFactory;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentReplyStatusEnum;
import com.deepexi.promotion.enums.CommentTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.mapper.CommentDetailMapper;
import com.deepexi.promotion.service.CommentDetailService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author admin
 */
@Service
@Slf4j
public class CommentDetailServiceImpl
        extends ServiceImpl<CommentDetailMapper, CommentDetailDO> implements CommentDetailService {

    @Resource
    private CommentDetailDAO commentDetailDAO;

    @Resource
    private StatisticTemplateFactory statisticTemplateFactory;

    @Resource
    private CommentDataServiceImpl commentDataService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentDetailDTO createReply(CommentDetailReplyInputDTO input) {
        CommentDetailDO commentDetailDO = Optional.ofNullable(commentDetailDAO.getById(input.getParentId()))
                .orElseThrow(() -> new ApplicationException(ResultEnum.RECORD_NOT_FOUND));
        CommentReplyStatusEnum oldReplyStatus = Optional.ofNullable(CommentReplyStatusEnum.getByStatus(commentDetailDO.getReplyStatus()))
                .orElse(CommentReplyStatusEnum.NO_REPLY);
        if (CommentReplyStatusEnum.NO_REPLY.equals(oldReplyStatus)){
            //更新父评论 回复状态 为已回复
            commentDetailDO.setReplyStatus(CommentReplyStatusEnum.REPLIED.getStatus());
            commentDetailDAO.updateById(commentDetailDO);
        }
        commentDetailDO.setCommentType(CommentTypeEnum.REPLY_COMMENT.getCode());
        commentDetailDO.setReplyStatus(CommentReplyStatusEnum.NO_REPLY.getStatus());
        commentDetailDO.setParentId(commentDetailDO.getId());
        commentDetailDO.setUserId(input.getUserId());
        if(input.getChannel() != null){
            commentDetailDO.setChannel(input.getChannel());
        }
        commentDetailDO.setId(null);
        commentDetailDO.setCreatedTime(null);
        commentDetailDO.setUpdatedTime(null);
        buildAndSaveData(CommentTypeEnum.REPLY_COMMENT,oldReplyStatus, commentDetailDO, input);
        return commentDetailDO.clone(CommentDetailDTO.class);
    }

    @Override
    public CommentDetailDTO addToComment(CommentDetailReplyInputDTO input) {
        //目前追加评论 只需要 资源(文本，图片，视频)
        //判断当前用户是否一致
        CommentDetailDO commentDetailDO = Optional.ofNullable(commentDetailDAO.getById(input.getParentId()))
                .orElseThrow(() -> new ApplicationException(ResultEnum.RECORD_NOT_FOUND));
        if (!input.getUserId().equals(commentDetailDO.getUserId())) {
            throw new ApplicationException(ResultEnum.USER_INCONSISTENCY);
        }
        commentDetailDO.setCommentType(CommentTypeEnum.ADDITIONAL_COMMENT.getCode());
        commentDetailDO.setReplyStatus(CommentReplyStatusEnum.NO_REPLY.getStatus());
        commentDetailDO.setParentId(commentDetailDO.getId());
        if(input.getChannel() != null){
            commentDetailDO.setChannel(input.getChannel());
        }
        commentDetailDO.setUserId(input.getUserId());
        commentDetailDO.setCreatedTime(null);
        commentDetailDO.setUpdatedTime(null);
        commentDetailDO.setId(null);
        buildAndSaveData(CommentTypeEnum.ADDITIONAL_COMMENT,null, commentDetailDO, input);
        return commentDetailDO.clone(CommentDetailDTO.class);
    }

    @Override
    public CommentDetailDTO getCommentDetailDTOById(Long detailId) {
        CommentDetailDO commentDetailDO = Optional.ofNullable(commentDetailDAO.getById(detailId))
                .orElseThrow(() -> new ApplicationException(ResultEnum.RECORD_NOT_FOUND));
        return commentDetailDO.clone(CommentDetailDTO.class);
    }

    private void buildAndSaveData(CommentTypeEnum commentTypeEnum, CommentReplyStatusEnum replyStatusEnum,
                                  CommentDetailDO commentDetailDO, CommentDetailReplyInputDTO input) {
        List<CommentResourceDO> commentResourceList = new ArrayList<>();
        List<CommentLabelDO> commentLabelList = new ArrayList<>();
        List<CommentStarDO> commentStarList = new ArrayList<>();
        AtomicLong insertSequence = new AtomicLong(0L);
        commentDetailDO.setInsertSequence(insertSequence.get());
        // 构建资源，标签，星评记录
        commentDataService.associatedData(insertSequence, input.getCommentResourceList(), input.getCommentLabelList(),
                input.getCommentStarList(), commentDetailDO, commentResourceList, commentLabelList, commentStarList);
        AbstractStatisticTemplate statisticTemplate = statisticTemplateFactory.getInstance(commentTypeEnum);
        List<CommentStatisticDO> statisticDoList = Lists.newArrayList();
        // 如果是回复评论，并且父评论没有回复记录才做统计
        if (!CommentTypeEnum.REPLY_COMMENT.equals(commentTypeEnum) ||
                CommentReplyStatusEnum.NO_REPLY.equals(replyStatusEnum)) {
            // 构建统计记录
            statisticDoList = buildStatisticData(input, commentDetailDO, statisticTemplate);
        }
        // 追评去重
        if (CommentTypeEnum.ADDITIONAL_COMMENT.equals(commentTypeEnum)) {
            // 追评去重,查找父评论统计记录
            CommentStatisticParamDTO parentStatisticInfo = commentDataService.getParentStatisticInfo(input.getParentId());
            List<CommentStatisticDO> parentStatisticDOS = ObjectCloneUtils.convertList(
                    statisticTemplate.count(Lists.newArrayList(parentStatisticInfo)), CommentStatisticDO.class);
            if (CollectionUtil.isNotEmpty(parentStatisticDOS)) {
                statisticDoList.removeAll(parentStatisticDOS);
            }
        }
        // 保存数据
        commentDataService.insertData(Lists.newArrayList(commentDetailDO), commentResourceList, commentLabelList,
                commentStarList, statisticDoList);
    }

    /**
     * 构建统计数据
     *
     * @param input         回评或追评入参
     * @param commentDetail 父评论明细
     * @return 统计记录列表
     */
    private List<CommentStatisticDO> buildStatisticData(CommentDetailReplyInputDTO input, CommentDetailDO commentDetail,
                                                        AbstractStatisticTemplate statisticTemplate) {
        CommentStatisticParamDTO statisticParamDTO = commentDetail.clone(CommentStatisticParamDTO.class);
        if (CollectionUtil.isNotEmpty(input.getCommentResourceList())) {
            statisticParamDTO.setCommentResourceList(input.getCommentResourceList());
        }
        if (CollectionUtil.isNotEmpty(input.getCommentLabelList())) {
            statisticParamDTO.setCommentLabelList(input.getCommentLabelList());
        }
        if (CollectionUtil.isNotEmpty(input.getCommentStarList())) {
            statisticParamDTO.setCommentStarList(input.getCommentStarList());
        }
        return ObjectCloneUtils.convertList(statisticTemplate.count(Lists.newArrayList(statisticParamDTO)), CommentStatisticDO.class);
    }


}