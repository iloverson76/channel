package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcStoreService;
import com.deepexi.channel.domain.eo.CcStore;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/门店信息表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccStores")
public class  CcStoreController {

    @Autowired
    private CcStoreService ccStoreService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcStore eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccStoreService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcStore eo) {
        return new Payload(ccStoreService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccStoreService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcStore")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStore eo) {
     eo.setId(pk);
     return new Payload(ccStoreService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcStore", notes = "创建CcStore")
    public Payload create(@RequestBody CcStore eo) {
        return new Payload(ccStoreService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcStore", notes = "根据id删除CcStore")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccStoreService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcStore", notes = "根据id批量删除CcStore")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccStoreService.delete(ids));
    }

}