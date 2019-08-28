package com.deepexi.channel.controller;

import com.deepexi.channel.domain.area.*;
import com.deepexi.channel.service.AreaService;
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
 * 区域-前端控制器
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
    AreaService areaService;

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
    @ApiOperation(value = "修改区域")
    public Payload<Boolean> updateArea(@RequestBody AreaVO vo) {
        return new Payload<>(true);
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取区域详情")
    public Payload<AreaVO> getAreaById(@PathVariable Long id){
        return new Payload<>(new AreaVO());
    }


    @GetMapping()
    @ApiOperation("查询区域列表")
    public Payload<PageBean<AreaVO>> listAreaPage(@ApiParam(name = "query", required = true) AreaQuery query){
        List<AreaVO> result = new ArrayList<>();
        result.add(new AreaVO());
        result.add(new AreaVO());
        return new Payload<>(new PageBean<>(result));
    }

    @GetMapping("/listLevel")
    @ApiOperation("查询区域层级元素列表")
    public Payload<PageBean<AreaVO>> listAreaLevelElement(@ApiParam(name = "query", required = true) AreaTreeQuery query){

        return new Payload<>(new PageBean<>(null));
    }

    @GetMapping("/listTree")
    @ApiOperation("查询区域树")
    public Payload<PageBean<AreaTreeVO>> listAreaTree(@ApiParam(name = "query", required = true) AreaTreeQuery query){

        return new Payload<>(new PageBean<>(null));
    }

}