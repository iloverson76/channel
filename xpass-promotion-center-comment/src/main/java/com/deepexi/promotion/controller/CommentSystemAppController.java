package com.deepexi.promotion.controller;

import java.util.Arrays;
import java.util.List;
//import io.swagger.annotations.*;

import javax.validation.Valid;

import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepexi.promotion.domain.CommentSystemAppInputVO;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.promotion.service.CommentSystemAppService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


//@Api(value = "/系统应用表", description = "$desc")
@RestController
@RequestMapping("/api/v1/comment/apps")
@Slf4j
public class  CommentSystemAppController {
    /**
     * 生成secret密钥
     */
    private final static String SECRET_KEY = "promotion";

    @Autowired
    private CommentSystemAppService commentSystemAppService;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;


    @GetMapping
    @ApiOperation(value = "查询所有应用", notes = "查询所有应用")
    public Payload<PageBean> findAllCommentSystemApp(CommentSystemAppQuery query) {
        List<CommentSystemAppDTO> list = commentSystemAppService.listAllCommentSystemApp(query);
        return new Payload<>(new PageBean<>(ObjectCloneUtils.convertList(list,CommentSystemAppVO.class, CloneDirection.OPPOSITE)));
    }

    @GetMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "查询单个应用明细", notes = "查询单个应用明细")
    public Payload getCommentSystemApp(@PathVariable(value = "id", required = true) Long  id) {
        CommentSystemAppDTO dto = commentSystemAppService.getCommentSystemApp(id);
        return new Payload<>(dto.clone(CommentSystemAppVO.class));
    }


    @PutMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据id修改", notes = "根据id修改CommentSystemApp")
    public Payload updateCommentSystemApp(@PathVariable(value = "id", required = true) Long  pk, @RequestBody @Valid CommentSystemAppUpdateVO vo) {
        CommentSystemAppDTO dto = vo.clone(CommentSystemAppDTO.class);
        return new Payload<>(commentSystemAppService.updateCommentSystemApp(pk, dto));
    }

    @PostMapping
    @ApiOperation(value = "创建CommentSystemApp", notes = "创建CommentSystemApp")
    public Payload createCommentSystemApp(@RequestBody @Valid CommentSystemAppInputVO vo) {
        CommentSystemAppDTO dto = vo.clone(CommentSystemAppDTO.class);
        return new Payload<>(commentSystemAppService.saveCommentSystemApp(dto));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据批量id删除CommentSystemApp", notes = "根据id删除CommentSystemApp")
    public Payload deleteCommentSystemApps(@PathVariable(value = "id", required = true) String ids) {
            return new Payload<>(commentSystemAppService.deleteCommentSystemApps(Arrays.stream(ids.split(",")).map(Long::parseLong).toArray(Long[]::new)));
    }


    @PutMapping("/{id:[a-zA-Z0-9,]+}/secrets/regain")
    @ApiOperation(value = "重新获取应用的secret",notes = "重新获取应用的secret")
    public Payload regainSecret(@PathVariable(value = "id", required = true) Long  pk){
        CommentSystemAppSecretDTO secret =  commentSystemAppService.regainSecret(pk);
        CommentSystemAppSecretVO vo = secret.clone(CommentSystemAppSecretVO.class);
        return new Payload<>(vo);
    }

    @PutMapping("/{id:[a-zA-Z0-9,]+}/enableCheck")
    @ApiOperation(value = "启用/禁用评价审核",notes = "启用/禁用评价审核")
    public Payload enableCommentCheck(@PathVariable(value = "id", required = true) Long  pk,@RequestBody @Valid CommentSystemAppEnbaleCheckVO vo){
        return new Payload<>(commentSystemAppService.enableAppCheck(pk,vo.getCommentCheck()));
    }

    @PutMapping("/{id:[a-zA-Z0-9,]+}/enable")
    @ApiOperation(value = "启用/禁用应用",notes = "启用/禁用应用")
    public Payload enableSystemApp(@PathVariable(value = "id", required = true) Long  pk,@RequestBody @Valid CommentSystemAppEnableVO vo){
        CommentSystemAppDTO dto = vo.clone(CommentSystemAppDTO.class);
        return new Payload<>(commentSystemAppService.enbaleApp(pk,vo.getEnable()));
    }

}