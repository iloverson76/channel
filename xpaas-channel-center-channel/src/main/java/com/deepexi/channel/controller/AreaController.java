package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.domain.eo.CcArea;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/区域表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccAreas")
public class AreaController {

    /*@Autowired
    private AreaService areaService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcArea eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(areaService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcArea eo) {
        return new Payload(areaService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(areaService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcArea")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcArea eo) {
     eo.setId(pk);
     return new Payload(areaService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcArea", notes = "创建CcArea")
    public Payload create(@RequestBody CcArea eo) {
        return new Payload(areaService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcArea", notes = "根据id删除CcArea")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(areaService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcArea", notes = "根据id批量删除CcArea")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(areaService.delete(ids));
    }*/

}