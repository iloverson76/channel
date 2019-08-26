package com.deepexi.channel.controller;

import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.domain.store.StoreTypeVO;
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
 * 门店类型表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/storeType")
@Api("门店类型管理")
public class StoreTypeController {
    @GetMapping()
    @ApiOperation("查询门店类型列表")
    public Payload<PageBean<StoreTypeVO>> listStoreTypePage(@ApiParam(name = "query", required = true) StoreTypeQuery query){
        List<StoreTypeVO> result = new ArrayList<>();
        result.add(new StoreTypeVO());
        result.add(new StoreTypeVO());
        return new Payload<>(new PageBean<>(result));
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取门店类型详情")
    public Payload<StoreTypeVO> getStoreTypeById(@PathVariable Long id){
        return new Payload<>(new StoreTypeVO());
    }

    @PostMapping()
    @ApiOperation(value = "保存门店类型")
    public Payload<Boolean> saveStoreType(@RequestBody StoreTypeVO vo) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "更新门店类型")
    public Payload<Boolean> updateStoreType(@RequestBody StoreTypeVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "批量删除门店类型")
    public Payload<Boolean> deleteStoreTypes(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }
}
