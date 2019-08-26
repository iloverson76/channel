package com.deepexi.channel.controller;

import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.chain.ChainVO;
import com.deepexi.channel.service.IAreaService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 区域表 前端控制器
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/channel/area")
@Api("区域管理")
public class AreaController {

    @Autowired
    IAreaService iAreaService;

    @PostMapping()
    @ApiOperation(value = "新增区域")
    public Payload<Boolean> saveArea(@RequestBody AreaVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "删除区域")
    public Payload<Boolean> deleteArea(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "更新区域")
    public Payload<Boolean> updateArea(@RequestBody AreaVO vo) {
        return new Payload<>(true);
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取区域详情")
    public Payload<AreaVO> getChainById(@PathVariable Long id){
        return new Payload<>(new AreaVO());
    }


    @GetMapping()
    @ApiOperation("查询区域列表")
    public Payload<PageBean<AreaVO>> listChainPage(@ApiParam(name = "query", required = true) AreaQuery query){
        List<AreaVO> result = new ArrayList<>();
        result.add(new AreaVO());
        result.add(new AreaVO());
        return new Payload<>(new PageBean<>(result));
    }

}