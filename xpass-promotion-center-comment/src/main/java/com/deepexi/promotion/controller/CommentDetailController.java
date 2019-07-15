package com.deepexi.promotion.controller;

import com.deepexi.promotion.domain.CommentDetailDTO;
import com.deepexi.promotion.domain.CommentDetailReplyInputDTO;
import com.deepexi.promotion.domain.CommentDetailReplyInputVO;
import com.deepexi.promotion.domain.CommentSystemAppDTO;
import com.deepexi.promotion.enums.CommentCheckStatusEnum;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.promotion.service.CommentDataService;
import com.deepexi.promotion.service.CommentDetailService;
import com.deepexi.promotion.service.CommentSystemAppService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author liaoxiaoxin
 */
@Api(value = "/评价明细表")
@RestController
@RequestMapping("/api/v1/comment/details")
public class CommentDetailController {

    @Resource
    private CommentDetailService commentDetailService;

    @Resource
    private CommentSystemAppService systemAppService;

    @Resource
    private AppRuntimeEnv appRuntimeEnv;

    @Resource
    private CommentDataService commentDataService;

    @PostMapping("/reply")
    public Payload reply(@Valid @RequestBody CommentDetailReplyInputVO input) {
        CommentDetailReplyInputDTO dto = input.clone(CommentDetailReplyInputDTO.class, CloneDirection.FORWARD);
        // 根据ID回查数据,并设置回dto
        commentDataService.queryStarInfoSetToReplyOrAdditionalData(dto);
        dto.setUserId(appRuntimeEnv.getUsername());
        return new Payload<>(commentDetailService.createReply(dto).getId());
    }

    @PostMapping("/addTo")
    public Payload addToComment(@Valid @RequestBody CommentDetailReplyInputVO input) {
        CommentDetailReplyInputDTO dto = input.clone(CommentDetailReplyInputDTO.class, CloneDirection.FORWARD);
        // 根据ID回查数据,并设置回dto
        commentDataService.queryStarInfoSetToReplyOrAdditionalData(dto);
        // 设置是否需要审核
        CommentDetailDTO commentDetailDTO = commentDetailService.getCommentDetailDTOById(input.getParentId());
        CommentSystemAppDTO commentSystemApp = systemAppService.getOneIfNotPresentThrowException(commentDetailDTO.getAppId());
        dto.setCheckStatus(Optional.ofNullable(commentSystemApp.getCommentCheck()).orElse(Boolean.FALSE)
                ? CommentCheckStatusEnum.UNCHECKED.getCode() : CommentCheckStatusEnum.CHECKED.getCode());
        dto.setUserId(appRuntimeEnv.getUsername());
        return new Payload<>(commentDetailService.addToComment(dto).getId());
    }

}