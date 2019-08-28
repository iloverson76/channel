package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.ChainService;
import com.deepexi.channel.domain.eo.CcChain;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/连锁表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccChains")
public class ChainController {

    @Autowired
    private ChainService chainService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcChain eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(chainService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcChain eo) {
        return new Payload(chainService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(chainService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcChain")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcChain eo) {
     eo.setId(pk);
     return new Payload(chainService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcChain", notes = "创建CcChain")
    public Payload create(@RequestBody CcChain eo) {
        return new Payload(chainService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcChain", notes = "根据id删除CcChain")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(chainService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcChain", notes = "根据id批量删除CcChain")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(chainService.delete(ids));
    }

}