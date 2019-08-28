package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcStoreGradeRelationService;
import com.deepexi.channel.domain.eo.CcStoreGradeRelation;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/门店-等级关联表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccStoreGradeRelations")
public class  CcStoreGradeRelationController {

    @Autowired
    private CcStoreGradeRelationService ccStoreGradeRelationService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcStoreGradeRelation eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccStoreGradeRelationService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcStoreGradeRelation eo) {
        return new Payload(ccStoreGradeRelationService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccStoreGradeRelationService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcStoreGradeRelation")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStoreGradeRelation eo) {
     eo.setId(pk);
     return new Payload(ccStoreGradeRelationService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcStoreGradeRelation", notes = "创建CcStoreGradeRelation")
    public Payload create(@RequestBody CcStoreGradeRelation eo) {
        return new Payload(ccStoreGradeRelationService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcStoreGradeRelation", notes = "根据id删除CcStoreGradeRelation")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccStoreGradeRelationService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcStoreGradeRelation", notes = "根据id批量删除CcStoreGradeRelation")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccStoreGradeRelationService.delete(ids));
    }

}