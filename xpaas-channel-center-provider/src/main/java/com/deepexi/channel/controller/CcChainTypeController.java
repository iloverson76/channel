package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcChainTypeService;
import com.deepexi.channel.domain.eo.CcChainType;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/连锁类型表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccChainTypes")
public class  CcChainTypeController {

    @Autowired
    private CcChainTypeService ccChainTypeService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcChainType eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccChainTypeService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcChainType eo) {
        return new Payload(ccChainTypeService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccChainTypeService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcChainType")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcChainType eo) {
     eo.setId(pk);
     return new Payload(ccChainTypeService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcChainType", notes = "创建CcChainType")
    public Payload create(@RequestBody CcChainType eo) {
        return new Payload(ccChainTypeService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcChainType", notes = "根据id删除CcChainType")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccChainTypeService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcChainType", notes = "根据id批量删除CcChainType")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccChainTypeService.delete(ids));
    }

}