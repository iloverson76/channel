package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.CommentTemplateTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.CommentBusinessLabelGroupSetService;
import com.deepexi.promotion.service.CommentBusinessModelConnectService;
import com.deepexi.promotion.service.*;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.deepexi.util.config.Payload;

import javax.validation.Valid;
import java.util.*;


/**
 * 模板配置接口
 *
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@RestController
@RequestMapping("/api/v1/comment/businesses")
@Slf4j
public class CommentBusinessSetController {
    /**
     * 文本关联表
     */
    @Autowired
    private CommentBusinessTextSetService commentBusinessTextSetService;
    /**
     * 评价对象关联表
     */
    @Autowired
    private CommentBusinessModelConnectService commentBusinessModelConnectService;
    /**
     * 标签组关联表
     */
    @Autowired
    private CommentBusinessLabelGroupSetService commentBusinessLabelGroupSetService;
    /**
     * 星级关联表
     */
    @Autowired
    private CommentBusinessStarSetService commentBusinessStarSetService;
    /**
     * 配置服务
     */
    @Autowired
    private CommentBusinessSetService commentBusinessSetService;


    @GetMapping("/{businessId}/templates")
    @ApiOperation("查询评价模本配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessId", value = "业务id"),
    })
    public Payload getCommentBusinessTextSet(@PathVariable(value = "businessId") Long businessId,
                                             @RequestParam(value = "isPublishing",defaultValue = "false") Boolean isPublishing) {
        CommentBusinessTemplateSetQuery setQuery = new CommentBusinessTemplateSetQuery();
        setQuery.setBusinessId(businessId);
        setQuery.setType(CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode());
        setQuery.setIsPublishing(isPublishing);
        List<CommentTemplateDTO> resultDTOList = commentBusinessSetService.queryTemplateList(setQuery);
        List<CommentTemplateVO> resultVOList = ObjectCloneUtils.convertList(resultDTOList, CommentTemplateVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(resultVOList);
    }


    @PutMapping("/{businessId}/templates/text/{id}")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("修改评价模板文本配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文本ID"),
            @ApiImplicitParam(name = "vo", value = "参数体"),
    })
    @SuppressWarnings("unchecked")
    public Payload updateCommentBusinessTextSet(@PathVariable(value = "id") Long id,
                                                @PathVariable(value = "businessId") Long businessId,
                                                @RequestBody @Valid CommentTemplateTextSetVO vo) {
        CommentSupportSettingDTO settingDTO = new CommentSupportSettingDTO();
        settingDTO.setSupportText(vo.getSupportText());
        settingDTO.setSupportPicture(vo.getSupportPicture());
        settingDTO.setSupportVideo(vo.getSupportVideo());

        CommentBusinessTextSetDTO commentBusinessTextSetDTO = new CommentBusinessTextSetDTO();
        commentBusinessTextSetDTO.setSupportSettings(settingDTO);
        Boolean flag = commentBusinessTextSetService.updateCommentBusinessTextSet(id, commentBusinessTextSetDTO);
        if (flag) {
            CommentBusinessModelConnectDTO connectDTO = new CommentBusinessModelConnectDTO();
            connectDTO.setUpdatedTime(new Date());
            connectDTO.setBusinessId(businessId);
            commentBusinessModelConnectService.updateCommentBusinessModelConnect(vo.getBusinessModelConnectId(), connectDTO);
        }
        return new Payload<>(flag);
    }

    @PostMapping("/{businessId}/templates/labelGroups/relations")
    @ApiOperation("新增标签组关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "input", value = "参数体")
    })
    public Payload addGroup(@PathVariable(value = "businessId") Long businessId,
                            @RequestBody @Valid CommentTemplateAddGroupVO input) {
        CommentTemplateAddGroupDTO inputDTO = input.clone(CommentTemplateAddGroupDTO.class);
        inputDTO.setLabelGroupIdList(input.getLabelGroupIdList());
        inputDTO.setType(CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode());
        inputDTO.setBusinessId(businessId);
        return new Payload<>(commentBusinessLabelGroupSetService.resetByCommentTemplateAddGroupDTO(inputDTO));
    }


    @DeleteMapping("/{businessId}/templates/labelGroups/{id}/relations")
    @ApiOperation("删除标签组关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签组ID"),
            @ApiImplicitParam(name = "businessModelConnectId", value = "业务对象关联关系主键")
    })
    public Payload deleteGroup(@PathVariable(value = "businessId") Long businessId,
                               @PathVariable(value = "id") Long id,
                               @RequestParam(value = "businessModelConnectId", required = true) long businessModelConnectId) {
        CommentTemplateDeleteGroupVO commentTemplateDeleteGroupVO = new CommentTemplateDeleteGroupVO();
        commentTemplateDeleteGroupVO.setBusinessModelConnectId(businessModelConnectId);
        commentTemplateDeleteGroupVO.setLabelGroupId(id);
        commentTemplateDeleteGroupVO.setType(CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode());
        return new Payload<>(commentBusinessLabelGroupSetService.deleteByVO(commentTemplateDeleteGroupVO));
    }


    @PostMapping("/{businessId}/templates/stars/relations")
    @ApiOperation("添加模板星级关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "input", value = "参数体")
    })
    public Payload addStarSet(@PathVariable(value = "businessId") Long businessId,
                              @RequestBody @Valid CommentBusinessStarSetInputVO input) {
        CommentBusinessStarSetInputDTO businessStarSetInputDTO = new CommentBusinessStarSetInputDTO();
        businessStarSetInputDTO.setBusinessModelConnectId(input.getBusinessModelConnectId());
        businessStarSetInputDTO.setStarIdList(input.getStarIdList());
        businessStarSetInputDTO.setType(CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode());
        businessStarSetInputDTO.setBusinessId(businessId);
        return new Payload<>(commentBusinessStarSetService.saveList(businessStarSetInputDTO));
    }


    @DeleteMapping("/{businessId}/templates/stars/{id}/relations")
    @ApiOperation("删除模板星级关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "星级模板ID"),
            @ApiImplicitParam(name = "businessModelConnectId", value = "业务对象关联关系主键")
    })
    public Payload delStarSet(@PathVariable(value = "businessId") Long businessId,
                              @PathVariable(value = "id") Long id,
                              @RequestParam(value = "businessModelConnectId", required = true) long businessModelConnectId) {
        CommentBusinessStarSetDeleteVO vo = new CommentBusinessStarSetDeleteVO();
        vo.setStarTemplateId(id);
        vo.setBusinessModelConnectId(businessModelConnectId);
        vo.setType(CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode());
        return new Payload<>(commentBusinessStarSetService.deleteByCondition(vo));
    }

    @PostMapping("/{businessId}/models/{modelId}/templates/replies")
    @ApiOperation("新增回评模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessId", value = "业务id"),
            @ApiImplicitParam(name = "modelId", value = "对象id"),
            @ApiImplicitParam(name = "vo", value = "回评模板配置")
    })
    public Payload createReplyTemplate(@PathVariable(value = "businessId") Long businessId,
                                       @PathVariable(value = "modelId") Long modelId,
                                       @RequestBody CommentBusinessReplyInputVO vo) {
        if (CollectionUtil.isEmpty(vo.getLabelTemplateList()) &&
                CollectionUtil.isEmpty(vo.getStarTemplateList()) && vo.getSupportSettings() == null) {
            log.error("保存回评模板没有内容");
            throw new ApplicationException(ResultEnum.PARAM_ERR);
        }
        CommentBusinessReplyInputDTO dto = CommentBusinessReplyInputDTO.builder().labelTemplateList(vo.getLabelTemplateList()).
                starTemplateList(vo.getStarTemplateList()).
                supportSettings(vo.getSupportSettings()).build();
        return new Payload<>(commentBusinessSetService.saveBusinessReplyTemplate(businessId, modelId, dto));
    }

    @GetMapping("/{businessId}/models/{modelId}/templates/replies")
    @ApiOperation("查询业务对象回复模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessId", value = "业务id"),
            @ApiImplicitParam(name = "modelId", value = "对象id")
    })
    public Payload getReplyTemplate(@PathVariable(value = "businessId") Long businessId,
                                    @PathVariable(value = "modelId") Long modelId) {
        CommentBusinessTemplateSetQuery setQuery = new CommentBusinessTemplateSetQuery();
        setQuery.setBusinessId(businessId);
        setQuery.setModelId(modelId);
        setQuery.setType(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode());
        setQuery.setIsPublishing(false);
        List<CommentTemplateDTO> resultDTOList = commentBusinessSetService.queryTemplateList(setQuery);
        List<CommentTemplateVO> resultVOList = ObjectCloneUtils.convertList(resultDTOList, CommentTemplateVO.class, CloneDirection.OPPOSITE);
        if(CollectionUtil.isEmpty(resultVOList)){
            return new Payload();
        }
        return new Payload<>(resultVOList.get(0));
    }

    @GetMapping("/{businessId}/models/{modelId}/templates/comments")
    @ApiOperation("查询业务对象评价模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessId", value = "业务id"),
            @ApiImplicitParam(name = "modelId", value = "对象id")
    })
    public Payload getCommentBusinessModelSet(@PathVariable(value = "businessId") Long businessId,
                                              @PathVariable(value = "modelId") Long modelId) {
        CommentBusinessTemplateSetQuery setQuery = new CommentBusinessTemplateSetQuery();
        setQuery.setBusinessId(businessId);
        setQuery.setModelId(modelId);
        setQuery.setType(CommentTemplateTypeEnum.COMMENT_TEMPLATE.getCode());
        setQuery.setIsPublishing(false);
        List<CommentTemplateDTO> resultDTOList = commentBusinessSetService.queryTemplateList(setQuery);
        List<CommentTemplateVO> resultVOList = ObjectCloneUtils.convertList(resultDTOList, CommentTemplateVO.class, CloneDirection.OPPOSITE);
        if(CollectionUtil.isEmpty(resultVOList)){
            return new Payload<>(new ArrayList<>());
        }
        return new Payload<>(resultVOList.get(0));
    }



    @PutMapping("/{businessId}/templates/replies/text/{id}")
    @Transactional(rollbackFor = Exception.class)
    @ApiOperation("修改回复模板文本配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文本ID"),
            @ApiImplicitParam(name = "vo", value = "参数体"),
    })
    @SuppressWarnings("unchecked")
    public Payload updateReplyTextSet(@PathVariable(value = "businessId") Long businessId,
                                      @PathVariable(value = "id") Long id,
                                      @RequestBody @Valid CommentTemplateTextSetVO vo) {
        CommentSupportSettingDTO settingDTO = new CommentSupportSettingDTO();
        settingDTO.setSupportText(vo.getSupportText());
        settingDTO.setSupportPicture(vo.getSupportPicture());
        settingDTO.setSupportVideo(vo.getSupportVideo());
        CommentBusinessTextSetDTO commentBusinessTextSetDTO = new CommentBusinessTextSetDTO();
        commentBusinessTextSetDTO.setSupportSettings(settingDTO);
        Boolean flag = commentBusinessTextSetService.updateCommentBusinessTextSet(id, commentBusinessTextSetDTO);
        if (flag) {
            CommentBusinessModelConnectDTO connectDTO = new CommentBusinessModelConnectDTO();
            connectDTO.setUpdatedTime(new Date());
            connectDTO.setBusinessId(businessId);
            commentBusinessModelConnectService.updateCommentBusinessModelConnect(vo.getBusinessModelConnectId(), connectDTO);
        }
        return new Payload<>(flag);
    }

    @PostMapping("/{businessId}/templates/replies/labelGroups/relations")
    @ApiOperation("新增回评标签组关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "input", value = "参数体")
    })
    public Payload addReplyGroup(@PathVariable(value = "businessId") Long businessId,
                            @RequestBody @Valid CommentTemplateAddGroupVO input) {
        CommentTemplateAddGroupDTO inputDTO = input.clone(CommentTemplateAddGroupDTO.class);
        inputDTO.setLabelGroupIdList(input.getLabelGroupIdList());
        inputDTO.setType(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode());
        return new Payload<>(commentBusinessLabelGroupSetService.resetByCommentTemplateAddGroupDTO(inputDTO));
    }


    @DeleteMapping("/{businessId}/templates/replies/labelGroups/{id}/relations")
    @ApiOperation("删除回评标签组关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签组ID"),
            @ApiImplicitParam(name = "businessModelConnectId", value = "业务对象关联关系主键")
    })
    public Payload deleteReplyGroup(@PathVariable(value = "businessId") Long businessId,
                                    @PathVariable(value = "id") Long id,
                                    @RequestParam(value = "businessModelConnectId", required = true) long businessModelConnectId) {
        CommentTemplateDeleteGroupVO commentTemplateDeleteGroupVO = new CommentTemplateDeleteGroupVO();
        commentTemplateDeleteGroupVO.setBusinessModelConnectId(businessModelConnectId);
        commentTemplateDeleteGroupVO.setLabelGroupId(id);
        commentTemplateDeleteGroupVO.setType(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode());
        return new Payload<>(commentBusinessLabelGroupSetService.deleteByVO(commentTemplateDeleteGroupVO));
    }


    @PostMapping("/{businessId}/templates/replies/stars/relations")
    @ApiOperation("添加模板星级关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "input", value = "参数体")
    })
    public Payload addReplyStarSet(@PathVariable(value = "businessId") Long businessId,
                                   @RequestBody @Valid CommentBusinessStarSetInputVO input) {
        CommentBusinessStarSetInputDTO businessStarSetInputDTO = new CommentBusinessStarSetInputDTO();
        businessStarSetInputDTO.setBusinessModelConnectId(input.getBusinessModelConnectId());
        businessStarSetInputDTO.setStarIdList(input.getStarIdList());
        businessStarSetInputDTO.setType(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode());
        return new Payload<>(commentBusinessStarSetService.saveList(businessStarSetInputDTO));
    }


    @DeleteMapping("/{businessId}/templates/replies/stars/{id}/relations")
    @ApiOperation("删除模板星级关联关系")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "星级模板ID"),
            @ApiImplicitParam(name = "businessModelConnectId", value = "业务对象关联关系主键")
    })
    public Payload delReplyStarSet(@PathVariable(value = "businessId") Long businessId,
                              @PathVariable(value = "id") Long id,
                              @RequestParam(value = "businessModelConnectId", required = true) long businessModelConnectId) {
        CommentBusinessStarSetDeleteVO vo = new CommentBusinessStarSetDeleteVO();
        vo.setStarTemplateId(id);
        vo.setBusinessModelConnectId(businessModelConnectId);
        vo.setType(CommentTemplateTypeEnum.REPLY_TEMPLATE.getCode());
        return new Payload<>(commentBusinessStarSetService.deleteByCondition(vo));
    }

}