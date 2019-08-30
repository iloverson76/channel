package com.deepexi.channel.controller;

import com.deepexi.channel.domain.store.StoreQuery;
import com.deepexi.channel.domain.store.StoreVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.StoreService;
import com.deepexi.channel.domain.eo.CcStore;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import io.swagger.annotations.*;


@Api(value = "/门店信息表", description = "查询门店列表")
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(@ApiParam(name = "query", required = true) StoreQuery query) {
        List<StoreVO> result = new ArrayList<>();
        result.add(new StoreVO());
        result.add(new StoreVO());
        return new Payload<>(new PageBean<>(result));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取门店详情")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeService.detail(pk));
    }

    @PutMapping("/{id}")
    @Transactional
    @ApiOperation(value = "根据id修改", notes = "根据id修改CcStore")
    public Payload update(@PathVariable(value = "id", required = true) Long pk, @RequestBody StoreVO vo) {
     vo.setId(pk);
     return new Payload(true);
    }

    @PostMapping
    @ApiOperation(value = "创建门店", notes = "创建门店")
    public Payload create(@RequestBody StoreVO vo) {
        return new Payload(true);
    }

    @DeleteMapping("/{ids}")
    @Transactional
    @ApiOperation(value = "根据id批量删除门店", notes = "根据id批量删除store")
    public Payload delete(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload(storeService.delete(ids));
    }

}