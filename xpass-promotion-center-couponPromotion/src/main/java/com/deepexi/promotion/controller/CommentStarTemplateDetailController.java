package com.deepexi.promotion.controller;

import com.deepexi.promotion.domain.CommentStarTemplateDetailDTO;
import com.deepexi.promotion.domain.CommentStarTemplateDetailVO;
import com.deepexi.promotion.service.CommentStarTemplateDetailService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhoust
 * @date 2019/5/23
 **/
@RestController
@RequestMapping("/xpaas-comment-center/api/v1/comment/star/template/detail")
public class CommentStarTemplateDetailController {

    @Autowired
    private CommentStarTemplateDetailService commentStarTemplateDetailService;

    @PostMapping
    public Payload create(@RequestBody @Valid CommentStarTemplateDetailVO vo) {
        return new Payload(commentStarTemplateDetailService.create(vo.clone(CommentStarTemplateDetailDTO.class)));
    }
}
