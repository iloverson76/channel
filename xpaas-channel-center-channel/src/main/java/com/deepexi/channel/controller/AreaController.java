package com.deepexi.channel.controller;

import com.deepexi.channel.service.IAreaService;
import com.deepexi.util.config.Payload;
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
public class AreaController {

    @Autowired
    IAreaService iAreaService;

    @GetMapping("/{id}")
    public Payload<Boolean> getAreaById(@PathVariable Long id){
        return new Payload<Boolean>(iAreaService.getById(id));
    }
}