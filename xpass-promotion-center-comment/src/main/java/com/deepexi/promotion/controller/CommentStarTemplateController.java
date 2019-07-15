package com.deepexi.promotion.controller;

import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.service.CommentStarTemplateService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@RestController
@RequestMapping("/api/v1/comment/apps/{appId}/stars/templates")
public class CommentStarTemplateController {

    @Autowired
    private CommentStarTemplateService commentStarTemplateService;

    @PostMapping
    public Payload create(@PathVariable(value = "appId") Long appId,
                          @RequestBody @Valid CommentStarTemplateVO vo) {
        vo.setAppId(appId);
        CommentStarTemplateDTO dto = vo.clone(CommentStarTemplateDTO.class, CloneDirection.FORWARD);
        return new Payload(commentStarTemplateService.create(dto));
    }

    @PutMapping("{id}")
    public Payload update(@PathVariable(value = "appId") Long appId,
                          @PathVariable(value = "id") Long id,
                          @RequestBody @Valid CommentStarTemplateVO vo) {
        vo.setAppId(appId);
        vo.setId(id);
        CommentStarTemplateDTO dto = vo.clone(CommentStarTemplateDTO.class, CloneDirection.FORWARD);
        return new Payload(commentStarTemplateService.update(dto));
    }

    @PostMapping("/details/")
    public Payload createStarDetail(@RequestParam(value = "appId") Long appId, @RequestBody @Valid CommentStarTemplateDetailInputVO vo) {
        CommentStarTemplateDetailDTO dto = vo.clone(CommentStarTemplateDetailDTO.class);
        dto.setSupportSettings(vo.getSupportSettings().clone(CommentStarTemplateDetailSupportSettingsDTO.class));
        dto.setAppId(appId);
        return new Payload<>(commentStarTemplateService.saveStarDetail(dto));
    }

    @PutMapping("/details/{id}")
    public Payload updateStarDetail(@RequestParam(value = "appId") Long appId, @PathVariable(value = "id") Long id, @RequestBody @Valid CommentStarTemplateDetailInputVO vo) {
        CommentStarTemplateDetailDTO dto = vo.clone(CommentStarTemplateDetailDTO.class);
        dto.setSupportSettings(vo.getSupportSettings().clone(CommentStarTemplateDetailSupportSettingsDTO.class));
        dto.setAppId(appId);
        return new Payload<>(commentStarTemplateService.updateStarDetail(id, dto));
    }

    @PutMapping("/details/{id}/enableSet")
    public Payload enableCommentStarSupportSet(@PathVariable(value = "id") Long id, CommentStarTemplateDetailSupportSettingsVO vo) {
        CommentStarTemplateDetailSupportSettingsDTO dto = vo.clone(CommentStarTemplateDetailSupportSettingsDTO.class);
        CommentStarTemplateDetailDTO detailDTO = new CommentStarTemplateDetailDTO();
        detailDTO.setSupportSettings(dto);
        return new Payload<>(commentStarTemplateService.updateStarDetail(id, detailDTO));
    }

    //    public Payload
    @GetMapping
    public Payload findPage(@PathVariable(value = "appId") Long appId,
                            @Valid CommentStarTemplatePageQuery query) {
        query.setAppId(appId);
        List<CommentStarTemplateDTO> starTemplateDTOList = commentStarTemplateService.selectPage(query);
        List<CommentStarTemplateVO> voList = ObjectCloneUtils.convertList(starTemplateDTOList, CommentStarTemplateVO.class, CloneDirection.OPPOSITE);

        return new Payload(new PageBean<>(voList));
    }

    @GetMapping("/{id}/histories")
    public Payload findHistories(@PathVariable(value = "appId") Long appId,
                                 @PathVariable("id") Long id,
                                 @Valid CommentStarTemplateHistoryQuery query) {
        query.setAppId(appId);
        query.setStarTemplateId(id);
        List<CommentStarTemplateHistoryVO> result = ObjectCloneUtils.convertList(commentStarTemplateService.selectHistoryPage(query), CommentStarTemplateHistoryVO.class, CloneDirection.OPPOSITE);
        return new Payload(new PageBean<>(result));
    }

    @GetMapping("/{id}")
    public Payload getStarTemplate(@PathVariable(value = "appId") Long appId,
                                   @PathVariable(value = "id") Long id) {
        CommentStarTemplateDTO starTemplateDTO = commentStarTemplateService.getStarTemplate(id);
        CommentStarTemplateVO vo = starTemplateDTO.clone(CommentStarTemplateVO.class, CloneDirection.OPPOSITE);
        return new Payload(vo);
    }

    @DeleteMapping("/{id}")
    public Payload delete(@PathVariable(value = "id") Long id) {
        return new Payload(commentStarTemplateService.delete(Collections.singleton(id)));
    }

    @DeleteMapping("/details/{id}")
    public Payload deleteCommentStarSupportSet(@RequestParam("starTemplateId") Long starTemplateId, @PathVariable(value = "id", required = true) Long id) {

        return new Payload<>(commentStarTemplateService.deleteStarDetail(starTemplateId, id));
    }

}
