package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcStoreChainService;
import com.deepexi.channel.domain.eo.CcStoreChain;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/门店-连锁关联表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccStoreChains")
public class  CcStoreChainController {

    @Autowired
    private CcStoreChainService ccStoreChainService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcStoreChain eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccStoreChainService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcStoreChain eo) {
        return new Payload(ccStoreChainService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccStoreChainService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcStoreChain")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStoreChain eo) {
     eo.setId(pk);
     return new Payload(ccStoreChainService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcStoreChain", notes = "创建CcStoreChain")
    public Payload create(@RequestBody CcStoreChain eo) {
        return new Payload(ccStoreChainService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcStoreChain", notes = "根据id删除CcStoreChain")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccStoreChainService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcStoreChain", notes = "根据id批量删除CcStoreChain")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccStoreChainService.delete(ids));
    }

}