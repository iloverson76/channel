package com.deepexi.channel.controller;

import com.deepexi.channel.domain.AreaDO;
import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaVO;
import com.deepexi.channel.service.IAreaService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/channel/area")
@Api("区域管理")
public class AreaController {

    @Autowired
    IAreaService iAreaService;

    @GetMapping("/{id}")
    @ApiOperation("根据id获取区域详情")
    public Payload<AreaVO> getAreaById(@PathVariable Long id){
        AreaVO vo=iAreaService.getById(id).clone(AreaVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(vo);
    }
/*
    @GetMapping("/{id}")
    @ApiOperation("根据id获取区域详情")
    public Payload<PageBean<AreaVO>> getAreaById(@PathVariable Long id){
        AreaVO vo=iAreaService.getById(id).clone(AreaVO.class, CloneDirection.OPPOSITE);
        ObjectCloneUtils.convertList(dtoList, AcceptedAndUsedListVO.class);
        return new Payload<>(new PageBean<>(vo));
    }*/
}