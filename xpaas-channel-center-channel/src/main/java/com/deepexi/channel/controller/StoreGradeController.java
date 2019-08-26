package com.deepexi.channel.controller;

import com.deepexi.channel.domain.chain.ChainVO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.channel.domain.store.StoreGradeVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 门店等级表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/storeGrade")
@Api("门店等级管理")
public class StoreGradeController {
    @GetMapping()
    @ApiOperation("查询门店等级列表")
    public Payload<PageBean<StoreGradeVO>> listStoreGradePage(@ApiParam(name = "query", required = true) StoreGradeQuery query){
        List<StoreGradeVO> result = new ArrayList<>();
        result.add(new StoreGradeVO());
        result.add(new StoreGradeVO());
        return new Payload<>(new PageBean<>(result));
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取门店等级详情")
    public Payload<StoreGradeVO> getStoreGradeById(@PathVariable Long id){
        return new Payload<>(new StoreGradeVO());
    }

    @PostMapping()
    @ApiOperation(value = "保存门店等级")
    public Payload<Boolean> saveStoreGrade(@RequestBody StoreGradeVO vo) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "更新门店等级")
    public Payload<Boolean> updateStoreGrade(@RequestBody StoreGradeVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "批量删除门店等级")
    public Payload<Boolean> deleteStoreGrades(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }
}
