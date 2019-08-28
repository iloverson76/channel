package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcDistributorGradeRelationService;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/经销商-等级表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccDistributorGradeRelations")
public class  CcDistributorGradeRelationController {

    @Autowired
    private CcDistributorGradeRelationService ccDistributorGradeRelationService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcDistributorGradeRelation eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccDistributorGradeRelationService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcDistributorGradeRelation eo) {
        return new Payload(ccDistributorGradeRelationService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccDistributorGradeRelationService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcDistributorGradeRelation")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcDistributorGradeRelation eo) {
     eo.setId(pk);
     return new Payload(ccDistributorGradeRelationService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcDistributorGradeRelation", notes = "创建CcDistributorGradeRelation")
    public Payload create(@RequestBody CcDistributorGradeRelation eo) {
        return new Payload(ccDistributorGradeRelationService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcDistributorGradeRelation", notes = "根据id删除CcDistributorGradeRelation")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccDistributorGradeRelationService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcDistributorGradeRelation", notes = "根据id批量删除CcDistributorGradeRelation")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccDistributorGradeRelationService.delete(ids));
    }

}