package com.deepexi.channel.controller;

import com.deepexi.channel.domain.store.StoreQuery;
import com.deepexi.channel.domain.store.StoreVO;
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
 * 门店信息表 前端控制器
 * </p>
 *
 * @author mumu
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/store")
@Api("门店管理")
public class StoreController {
    @GetMapping()
    @ApiOperation("查询门店列表")
    public Payload<PageBean<StoreVO>> listStorePage(@ApiParam(name = "query", required = true) StoreQuery query){
        List<StoreVO> result = new ArrayList<>();
        result.add(new StoreVO());
        result.add(new StoreVO());
        return new Payload<>(new PageBean<>(result));
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取门店详情")
    public Payload<StoreVO> getStoreById(@PathVariable Long id){
        return new Payload<>(new StoreVO());
    }

    @PostMapping()
    @ApiOperation(value = "保存门店")
    public Payload<Boolean> saveStore(@RequestBody StoreVO vo) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "更新门店")
    public Payload<Boolean> updateStore(@RequestBody StoreVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "批量删除门店")
    public Payload<Boolean> deleteStores(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

}
