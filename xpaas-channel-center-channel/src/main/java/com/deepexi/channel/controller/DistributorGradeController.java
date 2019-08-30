package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.domain.eo.CcDistributorGrade;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/经销商等级表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccDistributorGrades")
public class DistributorGradeController {

    @Autowired
    private DistributorGradeService distributorGradeService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcDistributorGrade eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(distributorGradeService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcDistributorGrade eo) {
        return new Payload(distributorGradeService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(distributorGradeService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcDistributorGrade")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcDistributorGrade eo) {
     eo.setId(pk);
     return new Payload(distributorGradeService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcDistributorGrade", notes = "创建CcDistributorGrade")
    public Payload create(@RequestBody CcDistributorGrade eo) {
        return new Payload(distributorGradeService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcDistributorGrade", notes = "根据id删除CcDistributorGrade")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(distributorGradeService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcDistributorGrade", notes = "根据id批量删除CcDistributorGrade")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(distributorGradeService.delete(ids));
    }

}