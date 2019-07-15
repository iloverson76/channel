package com.deepexi.promotion.controller;

import com.deepexi.promotion.domain.CommentCheckStatusVO;
import com.deepexi.promotion.domain.CommentDetailDTO;
import com.deepexi.promotion.enums.CommentCheckStatusEnum;
import com.deepexi.promotion.service.CommentDetailCheckService;
import com.deepexi.util.config.Payload;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/comment/detail/checks")
public class CommentDetailCheckController {

    @Resource
    private CommentDetailCheckService commentDetailCheckService;

    @PutMapping(value = "/batch/pass")
    public Payload<Boolean> batchPass(@RequestBody @Valid CommentCheckStatusVO checkStatusVO) {
        List<CommentDetailDTO> detailDTOList = checkStatusVO.getDetailIds().stream().map(
                item -> CommentDetailDTO.builder().id(item).checkStatus(CommentCheckStatusEnum.CHECKED.getCode()).build())
                .collect(Collectors.toList());
        return new Payload<>(commentDetailCheckService.updateBatch(detailDTOList));
    }

    @PutMapping(value = "/batch/rejects")
    public Payload<Boolean> batchReject(@RequestBody @Valid CommentCheckStatusVO checkStatusVO) {
        List<CommentDetailDTO> detailDTOList = checkStatusVO.getDetailIds().stream().map(
                item -> CommentDetailDTO.builder().id(item).checkStatus(CommentCheckStatusEnum.FAIL.getCode()).build())
                .collect(Collectors.toList());
        return new Payload<>(commentDetailCheckService.updateBatch(detailDTOList));
    }
}