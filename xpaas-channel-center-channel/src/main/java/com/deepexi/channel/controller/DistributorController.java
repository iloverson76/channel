package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.channel.domain.eo.CcDistributor;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;


@Api(value = "/经销商表", description = "$desc")
@RestController
@RequestMapping("/api/v1/distributor")
public class DistributorController {

    @Autowired
    private DistributorService distributorService;


    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcDistributor eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(distributorService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    @ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcDistributor eo) {
        return new Payload(distributorService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(distributorService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
@ApiOperation(value = "根据id修改", notes = "根据id修改CcDistributor")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcDistributor eo) {
     eo.setId(pk);
     return new Payload(distributorService.update(pk, eo));
    }

    @PostMapping
    @ApiOperation(value = "创建经销商", notes = "创建CcDistributor")
    public Payload create(@RequestBody CcDistributor eo) {
        return new Payload(distributorService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value = "根据id删除CcDistributor", notes = "根据id删除CcDistributor")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(distributorService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    @ApiOperation(value = "根据id批量删除CcDistributor", notes = "根据id批量删除CcDistributor")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(distributorService.delete(ids));
    }

}