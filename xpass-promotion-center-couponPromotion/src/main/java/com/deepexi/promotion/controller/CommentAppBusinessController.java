package com.deepexi.promotion.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//import io.swagger.annotations.*;

import com.alibaba.fastjson.JSONObject;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.CommentModelService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.Page;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepexi.promotion.service.CommentAppBusinessService;
import com.deepexi.util.config.Payload;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;


//@Api(value = "/应用业务表", description = "$desc")
@RestController
@RequestMapping("/api/v1/comment/businesses")
@Slf4j
public class CommentAppBusinessController {

    @Autowired
    private CommentAppBusinessService commentAppBusinessService;

    /**
     * code编码初始值
     */
    private final static int MIN_CODE = 1000;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public Payload<PageBean> findCommentAppBusinessPage(CommentAppBusinessQuery query) {
        // 查询全部
        List<CommentAppBusinessDTO> list = commentAppBusinessService.listAllCommentAppBusiness(query);
        List<CommentAppBusinessVO> commentAppBusinesses = ObjectCloneUtils.convertList(list, CommentAppBusinessVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(new PageBean<>(commentAppBusinesses));
    }

    @GetMapping("/{id:[a-zA-Z0-9,]+}")
    public Payload getCommentAppBusiness(@PathVariable(value = "id", required = true) Long id) {
        CommentAppBusinessDTO dto = commentAppBusinessService.getCommentAppBusiness(id);
        if (dto != null) {
            return new Payload<>(dto.clone(CommentAppBusinessVO.class));
        }
        return new Payload<>();
    }


    @PutMapping("/{id:[a-zA-Z0-9,]+}")
    //@ApiOperation(value = "根据id修改", notes = "根据id修改CommentAppBusiness")
    public Payload updateCommentAppBusiness(@PathVariable(value = "id", required = true) Long pk, @RequestBody @Valid CommentAppBusinessUpdateVO vo) {
        CommentAppBusinessDTO dto = vo.clone(CommentAppBusinessDTO.class);
        return new Payload<>(commentAppBusinessService.updateCommentAppBusiness(pk, dto));
    }

    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    //@ApiOperation(value = "创建CommentAppBusiness", notes = "创建CommentAppBusiness")
    public Payload createCommentAppBusiness(@RequestBody @Valid CommentAppBusinessInputVO vo) {
        CommentAppBusinessDTO dto = vo.clone(CommentAppBusinessDTO.class);
        return new Payload<>(commentAppBusinessService.saveCommentAppBusiness(dto));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    //@ApiOperation(value = "根据批量id删除CommentAppBusiness", notes = "根据id删除CommentAppBusiness")
    public Payload deleteCommentAppBusinesss(@PathVariable(value = "id", required = true) String ids) {
        return new Payload<>(commentAppBusinessService.deleteCommentAppBusinesss(Arrays.stream(ids.split(",")).map(Long::parseLong).toArray(Long[]::new)));
    }

    @PutMapping("/{id:[a-zA-Z0-9,]+}/enable")
    @ApiOperation(value = "启用禁用CommentAppBusiness", notes = "启用禁用CommentAppBusines")
    public Payload enableCommentAppBusiness(@PathVariable(value = "id", required = true) Long pk, @RequestBody @Valid CommentAppBusinessEnableVO vo) {
        CommentAppBusinessDTO dto = vo.clone(CommentAppBusinessDTO.class);
        return new Payload<>(commentAppBusinessService.updateCommentAppBusiness(pk, dto));
    }

}