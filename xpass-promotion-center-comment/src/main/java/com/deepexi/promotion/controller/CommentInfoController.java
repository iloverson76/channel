package com.deepexi.promotion.controller;

import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.BusinessModelConnectStatusEnum;
import com.deepexi.promotion.enums.CommentCheckStatusEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.promotion.service.*;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author admin
 */
@Api(value = "/评价表")
@RestController
@RequestMapping("/api/v1/comment/contents")
public class CommentInfoController {

    @Resource
    private CommentInfoService commentService;

    @Resource
    private CommentSystemAppService systemAppService;

    @Resource
    private AppRuntimeEnv appRuntimeEnv;

    @Resource
    private CommentBusinessModelConnectService businessModelConnectService;

    @Resource
    private CommentAppBusinessService appBusinessService;

    @Resource
    private CommentStarTemplateDetailService starTemplateDetailService;

    @Resource
    private CommentDataService commentDataService;

    @PostMapping
    @ApiOperation(value = "创建Comment", notes = "创建Comment")
    public Payload createComment(@RequestBody @Valid CommentInfoInputVO input) {
        CommentAppBusinessDTO businessDTOParam = CommentAppBusinessDTO.builder()
                .code(input.getCode()).tenantId(appRuntimeEnv.getTenantId()).build();
        CommentAppBusinessDTO appBusinessDTO = appBusinessService.getByCommentAppBusinessDTO(businessDTOParam);
        CommentInfoInputDTO commentInfoInputDTO = input.clone(CommentInfoInputDTO.class, CloneDirection.FORWARD);
        // 设置appId,appBusinessId,appBusinessName
        commentInfoInputDTO.setAppId(appBusinessDTO.getAppId());
        commentInfoInputDTO.setAppBusinessId(appBusinessDTO.getId());
        commentInfoInputDTO.setAppBusinessName(appBusinessDTO.getName());
        // 回查星评数据，并设置到DTO
        commentDataService.queryStarInfoSetToPublishData(commentInfoInputDTO);
        // 设置评论是否审核
        CommentSystemAppDTO app = systemAppService.getOneIfNotPresentThrowException(commentInfoInputDTO.getAppId());
        commentInfoInputDTO.setCheckStatus(
                Optional.ofNullable(app.getCommentCheck()).orElse(Boolean.FALSE) ?
                        CommentCheckStatusEnum.UNCHECKED.getCode() : CommentCheckStatusEnum.CHECKED.getCode());
        commentInfoInputDTO.setUserId(appRuntimeEnv.getUsername());
        return new Payload<>(commentService.createComment(commentInfoInputDTO));
    }

    @GetMapping
    @ApiOperation(value = "分页查询评价内容列表")
    public Payload getComments(@Valid CommentInfoQuery query) {
        query.setTenantId(appRuntimeEnv.getTenantId());
        List<CommentInfoQueryDTO> commentInfoQueryDTOS = commentService.listCommentInfoQueryDTO(query);
        List<CommentInfoQueryVO> commentInfoQueryVOS =
                ObjectCloneUtils.convertList(commentInfoQueryDTOS, CommentInfoQueryVO.class, CloneDirection.OPPOSITE);
        //查询业务是否启用
        Set<Long> businessIds = commentInfoQueryVOS.stream()
                .map(CommentInfoQueryVO::getAppBusinessId).collect(Collectors.toSet());
        Map<Long, Boolean> businessDTOMap = appBusinessService.listAllCommentAppBusiness(new ArrayList<>(businessIds))
                .stream().collect(Collectors.toMap(CommentAppBusinessDTO::getId, CommentAppBusinessDTO::getEnable));

        //查询是否有回评模板
        Set<CommentBusinessModelConnectQuery> connectQuery = commentInfoQueryVOS.stream().map(
                queryVo -> CommentBusinessModelConnectQuery.builder()
                        .businessId(queryVo.getAppBusinessId()).modelId(queryVo.getModelId()).build())
                .collect(Collectors.toSet());
        List<CommentBusinessModelConnectDTO> connectDTOS =
                businessModelConnectService.listCommentBusinessModelConnects(new ArrayList<>(connectQuery));
        // map: key = businessId-modelId value = isReply
        //同时过滤业务模板禁用的
        Map<String, Integer> isReplyMap = connectDTOS.stream()
                .filter(item -> Optional.ofNullable(businessDTOMap.get(item.getBusinessId())).orElse(Boolean.FALSE))
                .collect(Collectors.toMap(
                        item -> StringUtils.joinWith("-", item.getBusinessId(), item.getModelId()),
                        item -> item.getIsReply() != null ?
                                item.getIsReply() : BusinessModelConnectStatusEnum.COMMENT_DISABLE.getCode(),
                        (oldValue, newValue) -> BusinessModelConnectStatusEnum.COMMENT_DISABLE.getCode().equals(oldValue)
                                ? newValue : oldValue
                ));
        commentInfoQueryVOS.forEach(item -> {
            Integer flag = Optional.ofNullable(
                    isReplyMap.get(StringUtils.joinWith("-", item.getAppBusinessId(), item.getModelId())))
                    .orElse(BusinessModelConnectStatusEnum.COMMENT_DISABLE.getCode());
            item.setCanReply(BusinessModelConnectStatusEnum.COMMENT_ENABLE.getCode().equals(flag)
                    ? Boolean.TRUE : Boolean.FALSE);
        });
        return new Payload<>(new PageBean<>(commentInfoQueryVOS));
    }

    @GetMapping("/{commentDetailId}")
    @ApiOperation(value = "查询评论详情")
    public Payload getCommentDetail(@PathVariable Long commentDetailId) {
        CommentInfoQuery query = CommentInfoQuery.builder().commentDetailId(commentDetailId).build();
        CommentDetailResultDTO commentDetailResultDTO = commentService.getCommentDetailWithTree(query);
        CommentDetailResultVO result = commentDetailResultDTO.clone(CommentDetailResultVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(result);
    }

    @GetMapping("{commentDetailId}/star")
    @ApiOperation("查询星评评价")
    public Payload getStarInfo(@PathVariable("commentDetailId") Long commentDetailId) {
        List<CommentStarDTO> commentStarDTOList = commentService.listCommentStarDTO(commentDetailId);
        List<Long> detailIds = commentStarDTOList.stream()
                .map(CommentStarDTO::getStarTemplateDetailId).collect(Collectors.toList());
        // 请求星评模块 取得别名
        List<CommentStarTemplateDetailDTO> starTemplateDetailDTOS =
                starTemplateDetailService.queryByIds(detailIds);
        Map<Long, String> nickNameMap = starTemplateDetailDTOS.stream().collect(Collectors.toMap(
                CommentStarTemplateDetailDTO::getId, CommentStarTemplateDetailDTO::getNickName,
                (oldValue, newValue) -> newValue));
        commentStarDTOList.forEach(item -> item.setStarAlias(nickNameMap.get(item.getStarTemplateDetailId())));

        List<CommentStarVO> commentStarVOList =
                ObjectCloneUtils.convertList(commentStarDTOList, CommentStarVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(commentStarVOList);
    }

    @DeleteMapping("/{commentDetailId}")
    @ApiOperation("删除评论")
    public Payload deleteComment(@PathVariable("commentDetailId") Long commentDetailId) {
        if (commentDetailId <= 0L) {
            throw new ApplicationException(ResultEnum.RECORD_NOT_FOUND);
        }
        return new Payload<>(commentService.deleteComment(commentDetailId));
    }

    @GetMapping("/counts")
    @ApiOperation("评论数量统计")
    public Payload countComment(@Valid CommentCountQuery countQuery) {
        //校验应用是否存在
        systemAppService.getOneIfNotPresentThrowException(countQuery.getAppId());
        countQuery.setTenantId(appRuntimeEnv.getTenantId());
        return new Payload<>(commentService.commentCount(countQuery));
    }


}