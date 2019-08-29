package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.channel.domain.eo.CcStoreType;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/门店类型表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccStoreTypes")
public class StoreTypeController {

    @Autowired
    private StoreTypeService storeTypeService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcStoreType eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(storeTypeService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcStoreType eo) {
        return new Payload(storeTypeService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeTypeService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcStoreType")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStoreType eo) {
     eo.setId(pk);
     return new Payload(storeTypeService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcStoreType", notes = "创建CcStoreType")
    public Payload create(@RequestBody CcStoreType eo) {
        return new Payload(storeTypeService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcStoreType", notes = "根据id删除CcStoreType")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeTypeService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcStoreType", notes = "根据id批量删除CcStoreType")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(storeTypeService.delete(ids));
    }

}