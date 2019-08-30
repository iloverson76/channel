package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.StoreDistributorRelationService;
import com.deepexi.channel.domain.eo.CcStoreDistributorRelation;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/门店-经销商商-等级体系关联表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccStoreDistributorRelations")
public class StoreDistributorRelationController {

    @Autowired
    private StoreDistributorRelationService storeDistributorRelationService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcStoreDistributorRelation eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(storeDistributorRelationService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcStoreDistributorRelation eo) {
        return new Payload(storeDistributorRelationService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeDistributorRelationService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcStoreDistributorRelation")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStoreDistributorRelation eo) {
     eo.setId(pk);
     return new Payload(storeDistributorRelationService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcStoreDistributorRelation", notes = "创建CcStoreDistributorRelation")
    public Payload create(@RequestBody CcStoreDistributorRelation eo) {
        return new Payload(storeDistributorRelationService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcStoreDistributorRelation", notes = "根据id删除CcStoreDistributorRelation")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeDistributorRelationService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcStoreDistributorRelation", notes = "根据id批量删除CcStoreDistributorRelation")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(storeDistributorRelationService.delete(ids));
    }

}