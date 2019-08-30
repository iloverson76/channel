package com.deepexi.channel.controller;

import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.channel.domain.store.StoreGradeVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.channel.domain.eo.CcStoreGrade;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.List;


@Api(value = "/门店等级管理", description = "门店等级管理")
@RestController
@RequestMapping("/api/v1/storeGrade")
public class StoreGradeController {

    @Autowired
    private StoreGradeService storeGradeService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "查询门店等级列表,传-1时获取整个列表")
    public  Payload findPage(@ApiParam(name = "query", required = true) StoreGradeQuery query) {
        List<StoreGradeVO> result = new ArrayList<>();
        result.add(new StoreGradeVO());
        result.add(new StoreGradeVO());
        return new Payload<>(new PageBean<>(result));
    }

//    @GetMapping("/list")
//    @ApiOperation(value = "树形查询", notes = "查询全部请求")
//    public Payload findAll(CcStoreGrade eo) {
//        return new Payload(storeGradeService.findAll(eo));
//    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeGradeService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
    @ApiOperation(value = "根据id修改", notes = "根据id修改CcStoreGrade")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcStoreGrade eo) {
     eo.setId(pk);
     return new Payload(storeGradeService.update(pk, eo));
    }

    @PostMapping
    @ApiOperation(value = "创建CcStoreGrade", notes = "创建CcStoreGrade")
    public Payload create(@RequestBody CcStoreGrade eo) {
        return new Payload(storeGradeService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ApiOperation(value = "根据id删除CcStoreGrade", notes = "根据id删除CcStoreGrade")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeGradeService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    @ApiOperation(value = "根据id批量删除CcStoreGrade", notes = "根据id批量删除CcStoreGrade")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(storeGradeService.delete(ids));
    }

}