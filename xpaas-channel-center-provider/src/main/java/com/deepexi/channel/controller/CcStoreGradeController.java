package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcStoreGradeService;
import com.deepexi.channel.domain.eo.CcStoreGrade;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/门店等级表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccStoreGrades")
public class  CcStoreGradeController {

    @Autowired
    private CcStoreGradeService ccStoreGradeService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcStoreGrade eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccStoreGradeService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcStoreGrade eo) {
        return new Payload(ccStoreGradeService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccStoreGradeService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcStoreGrade")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStoreGrade eo) {
     eo.setId(pk);
     return new Payload(ccStoreGradeService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcStoreGrade", notes = "创建CcStoreGrade")
    public Payload create(@RequestBody CcStoreGrade eo) {
        return new Payload(ccStoreGradeService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcStoreGrade", notes = "根据id删除CcStoreGrade")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccStoreGradeService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcStoreGrade", notes = "根据id批量删除CcStoreGrade")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccStoreGradeService.delete(ids));
    }

}