package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcDistributorAreaService;
import com.deepexi.channel.domain.eo.CcDistributorArea;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/经销商-区域表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccDistributorAreas")
public class  CcDistributorAreaController {

    @Autowired
    private CcDistributorAreaService ccDistributorAreaService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcDistributorArea eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccDistributorAreaService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcDistributorArea eo) {
        return new Payload(ccDistributorAreaService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccDistributorAreaService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcDistributorArea")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcDistributorArea eo) {
     eo.setId(pk);
     return new Payload(ccDistributorAreaService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcDistributorArea", notes = "创建CcDistributorArea")
    public Payload create(@RequestBody CcDistributorArea eo) {
        return new Payload(ccDistributorAreaService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcDistributorArea", notes = "根据id删除CcDistributorArea")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccDistributorAreaService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcDistributorArea", notes = "根据id批量删除CcDistributorArea")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccDistributorAreaService.delete(ids));
    }

}