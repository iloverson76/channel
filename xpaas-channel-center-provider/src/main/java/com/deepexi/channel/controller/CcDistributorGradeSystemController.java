package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcDistributorGradeSystemService;
import com.deepexi.channel.domain.eo.CcDistributorGradeSystem;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/等级体系表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccDistributorGradeSystems")
public class  CcDistributorGradeSystemController {

    @Autowired
    private CcDistributorGradeSystemService ccDistributorGradeSystemService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcDistributorGradeSystem eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccDistributorGradeSystemService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcDistributorGradeSystem eo) {
        return new Payload(ccDistributorGradeSystemService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccDistributorGradeSystemService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcDistributorGradeSystem")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcDistributorGradeSystem eo) {
     eo.setId(pk);
     return new Payload(ccDistributorGradeSystemService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcDistributorGradeSystem", notes = "创建CcDistributorGradeSystem")
    public Payload create(@RequestBody CcDistributorGradeSystem eo) {
        return new Payload(ccDistributorGradeSystemService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcDistributorGradeSystem", notes = "根据id删除CcDistributorGradeSystem")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccDistributorGradeSystemService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcDistributorGradeSystem", notes = "根据id批量删除CcDistributorGradeSystem")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccDistributorGradeSystemService.delete(ids));
    }

}