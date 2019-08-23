package com.deepexi.channel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 连锁表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/chain")
@Api("连锁管理")
public class ChainController {

    @GetMapping("{id}")
    @ApiOperation("根据id获取连锁店详情")
    public void getChainById(Integer id){

    }

}
