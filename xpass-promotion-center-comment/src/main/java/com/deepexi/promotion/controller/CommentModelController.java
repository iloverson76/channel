package com.deepexi.promotion.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.deepexi.promotion.constant.CommentConstants;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.service.CommentBusinessModelConnectService;
import com.deepexi.promotion.utils.RedisUtils;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepexi.promotion.enums.CommentMdelCodeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.CommentModelHistoryService;
import com.deepexi.promotion.service.CommentModelService;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;


/**
 * 评价对象应用层接口
 *
 * @author zhangyanping
 * @date 2019/5/7 17:23:00
 */
@RestController
@RequestMapping("/api/v1/comment/models")
public class CommentModelController {

    @Autowired
    private CommentModelService commentModelService;

    @Autowired
    private CommentModelHistoryService commentModelHistoryService;

    @Autowired
    private CommentBusinessModelConnectService commentBusinessModelConnectService;


    /**
     * code字段1000起
     */
    private static final int CODE_MIN = 1000;

    /**
     * 多条件查询评价对象列表
     *
     * @param query 查询对象
     * @return Payload
     */
    @GetMapping
    public Payload findCommentModelPage(@Valid CommentModelQuery query) {
        List<CommentModelDTO> list = commentModelService.findCommentModelPage(query);
        return new Payload<>(new PageBean<>(
                ObjectCloneUtils.convertList(list, CommentModelVO.class, CloneDirection.OPPOSITE)));
    }

    /**
     * 查询所有评价对象
     *
     * @param query 查询对象
     * @return Payload
     */
    @GetMapping("/list")
    public Payload findAllCommentModel(CommentModelQuery query) {
        query.setPage(-1);
        //查已开启的
        query.setIsEnable(1);
        return new Payload<>(ObjectCloneUtils.convertList(
                commentModelService.findCommentModelPage(query),
                CommentModelVO.class,
                CloneDirection.OPPOSITE
        ));
    }

    /**
     * 查询评价对象所有的评价历史
     *
     * @param query 查询对象
     * @return Payload
     */
    @GetMapping("/{modelId}/history/list")
    public Payload findAllCommentModelHistory(@PathVariable(value = "modelId", required = true) Long pk, @Valid CommentModelHistoryQuery query) {
        query.setModelId(pk);
        query.setPage(-1);
        List<CommentModelHistoryDTO> list = commentModelHistoryService.findCommentModelHistoryPage(query);
        return new Payload<>(ObjectCloneUtils.convertList(
                list,
                CommentModelHistoryVO.class,
                CloneDirection.OPPOSITE)
        );
    }


    /**
     * 根据主键修改
     *
     * @param pk 主键
     * @param vo 修改对象
     * @return 成功或失败
     */
    @PutMapping("/{id}")
    public Payload updateCommentModel(@PathVariable(value = "id", required = true) Long pk, @RequestBody @Valid CommentModelUpdateVO vo) {
        //修改状态时 不用添加记录，修改名称或者标识码时判断修改类型并且添加历史记录
        if (StringUtil.isNotBlank(vo.getName()) || StringUtil.isNotBlank(vo.getIdentificationCode())) {
            CommentModelDTO commentModelDTO = commentModelService.getCommentModel(pk);
            int updateType = 0;
            //如果修改了名称
            if (null != vo.getName() && !vo.getName().equals(commentModelDTO.getName())) {
                //判断该名称是否存在
                CommentModelDTO checkCondition = new CommentModelDTO();
                checkCondition.setName(vo.getName());
                if (commentModelService.getCountCondition(checkCondition)) {
                    throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
                } else {
                    updateType = CommentMdelCodeEnum.NAME_CHANGE.getCode();
                }
            }
            if (null != vo.getIdentificationCode() && !vo.getIdentificationCode().equals(commentModelDTO.getIdentificationCode())) {
                CommentModelDTO checkCondition = new CommentModelDTO();
                checkCondition.setIdentificationCode(vo.getIdentificationCode());
                if (commentModelService.getCountCondition(checkCondition)) {
                    throw new ApplicationException(ResultEnum.IDENTIFICATIONCODE_EXIST);
                }
                if (updateType == CommentMdelCodeEnum.NAME_CHANGE.getCode()) {
                    //如果修改了名称和标识码
                    updateType = CommentMdelCodeEnum.NAME_CODE_CHANGE.getCode();
                } else {
                    //如果修改了标识码
                    updateType = CommentMdelCodeEnum.IDENTI_CODE_CHANGE.getCode();
                }
            }
            if (updateType != 0) {
                CommentModelHistoryDTO historyDto = commentModelDTO.clone(CommentModelHistoryDTO.class);
                historyDto.setId(null);
                historyDto.setModelId(commentModelDTO.getId());
                historyDto.setUpdateType(updateType);
                historyDto.setCreatedBy(null);
                historyDto.setCreatedTime(null);
                historyDto.setUpdatedTime(null);
                historyDto.setUpdatedBy(null);
                historyDto.setTenantId(null);
                commentModelHistoryService.createCommentModelHistory(historyDto);
            }
        }
        CommentModelDTO commentModelDTO = vo.clone(CommentModelDTO.class);
        commentModelDTO.setIsCreate(Boolean.FALSE);
        //修改
        boolean flag = commentModelService.updateCommentModel(pk, commentModelDTO);
        return new Payload<>(flag);
    }

    /**
     * 添加评价对象
     *
     * @param vo 保存对象
     * @return Payload
     */
    @PostMapping
    public Payload createCommentModel(@RequestBody @Valid CommentModelVO vo) {
        //判断该名称是否存在
        CommentModelDTO checkCondition = new CommentModelDTO();
        checkCondition.setName(vo.getName());
        if (commentModelService.getCountCondition(checkCondition)) {
            throw new ApplicationException(ResultEnum.NAME_DUPLICATE);
        }
        checkCondition.setName(null);
        checkCondition.setIdentificationCode(vo.getIdentificationCode());
        if (commentModelService.getCountCondition(checkCondition)) {
            throw new ApplicationException(ResultEnum.IDENTIFICATIONCODE_EXIST);
        }
        vo.setCode("0");
        CommentModelDTO dto = vo.clone(CommentModelDTO.class);
        dto.setIsCreate(Boolean.TRUE);
        boolean flag = commentModelService.createCommentModel(dto);
        dto.setCode(CODE_MIN + dto.getId() + "");
        if (flag) {
            commentModelService.updateCommentModel(dto.getId(), dto);
        }
        return new Payload<>(flag);
    }

    /**
     * 删除评价对象 并且删除和评价对象历史关联数据
     *
     * @param ids 删除id数组
     * @return Payload
     */
    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    public Payload deleteCommentModels(@PathVariable(value = "id", required = true) String ids) {
        Long[] modelIds = Arrays.stream(ids.split(",")).map(Long::parseLong).toArray(Long[]::new);
        Boolean flag = commentModelService.deleteCommentModels(modelIds);
        if (flag) {
            //用于清除缓存
            List<CommentBusinessModelConnectDO> commentBusinessModelConnectList = commentBusinessModelConnectService.selectByCondition(modelIds);
            //删除时要删除对应业务对象关联表的所有关联数据
            commentBusinessModelConnectService.deleteByCondition(modelIds);
            commentModelHistoryService.deleteBatchByModelIds(modelIds);
            //清除缓存
            List<Long> businessIds = commentBusinessModelConnectList.stream().map(CommentBusinessModelConnectDO::getBusinessId).collect(Collectors.toList());
            RedisUtils.deleteCache(CommentConstants.TEMPLATE_INFO_KEY, businessIds);
        }
        return new Payload<>(flag);
    }
}