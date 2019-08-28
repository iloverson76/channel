package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcAreaTypeService;
import com.deepexi.channel.domain.eo.CcAreaType;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/区域类型表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccAreaTypes")
public class  CcAreaTypeController {

    @Autowired
    private CcAreaTypeService ccAreaTypeService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcAreaType eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccAreaTypeService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcAreaType eo) {
        return new Payload(ccAreaTypeService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccAreaTypeService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcAreaType")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcAreaType eo) {
     eo.setId(pk);
     return new Payload(ccAreaTypeService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcAreaType", notes = "创建CcAreaType")
    public Payload create(@RequestBody CcAreaType eo) {
        return new Payload(ccAreaTypeService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcAreaType", notes = "根据id删除CcAreaType")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccAreaTypeService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcAreaType", notes = "根据id批量删除CcAreaType")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccAreaTypeService.delete(ids));
    }

}