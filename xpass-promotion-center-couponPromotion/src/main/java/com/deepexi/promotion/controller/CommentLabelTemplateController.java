package com.deepexi.promotion.controller;

import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.service.CommentLabelTemplateService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/v1/comment/labels")
@Slf4j
public class CommentLabelTemplateController {

    @Autowired
    private CommentLabelTemplateService commentLabelTemplateService;


    @GetMapping
    public Payload findPage(@Valid CommentLabelTemplateQuery query) {
        List<CommentLabelTemplateDTO> page = commentLabelTemplateService.findPage(query);
        List<CommentLabelTemplateVO> result = ObjectCloneUtils.convertList(page, CommentLabelTemplateVO.class, CloneDirection.OPPOSITE);
        return new Payload(new PageBean<>(result));
    }


    @GetMapping("/{id}/histories")
    public Payload findHistoryPage(@PathVariable(value = "id", required = true) Long pk, @Valid CommentLabelTemplateHistoryQuery query) {
        query.setTemplateId(pk);
        List<CommentLabelTemplateHistoryDTO> historyPage = commentLabelTemplateService.findHistoryPage(query);
        List<CommentLabelTemplateHistoryVO> result = ObjectCloneUtils.convertList(historyPage, CommentLabelTemplateHistoryVO.class, CloneDirection.OPPOSITE);
        return new Payload(new PageBean<>(result));
    }

    @PutMapping("/{id}")
    public Payload update(@PathVariable(value = "id", required = true) Long pk, @RequestBody @Valid  CommentLabelTemplateVO vo) {
        CommentLabelTemplateDTO dto = vo.clone(CommentLabelTemplateDTO.class);

        return new Payload(commentLabelTemplateService.update(pk, dto));
    }

    @PostMapping
    public Payload create(@RequestBody @Valid CommentLabelTemplateVO vo) {
        return new Payload(commentLabelTemplateService.create(vo.clone(CommentLabelTemplateDTO.class)));
    }

    @DeleteMapping("/{id}")
    public Payload delete(@PathVariable(value = "id", required = true) Long pk) {
        return new Payload(commentLabelTemplateService.delete(pk));
    }

}