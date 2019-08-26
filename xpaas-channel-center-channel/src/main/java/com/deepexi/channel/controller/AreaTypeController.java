package com.deepexi.channel.controller;


import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.domain.area.AreaTypeVO;
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
 * 区域类型-前端控制器
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/areaType")
@Api("区域类型管理")
public class AreaTypeController {

    @PostMapping()
    @ApiOperation(value = "新增区域分类")
    public Payload<Boolean> saveAreaType(@RequestBody AreaTypeVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "删除区域分类")
    public Payload<Boolean> deleteAreaType(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "更新区域分类")
    public Payload<Boolean> updateAreaType(@RequestBody AreaTypeVO vo) {
        return new Payload<>(true);
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取区域详情")
    public Payload<AreaTypeVO> getAreaTypeById(@PathVariable Long id){
        return new Payload<>(new AreaTypeVO());
    }

    @GetMapping()
    @ApiOperation("查询区域分类列表")
    public Payload<PageBean<AreaTypeVO>> listAreaTypePage(@ApiParam(name = "query", required = true) AreaTypeQuery query){
        List<AreaTypeVO> result = new ArrayList<>();
        result.add(new AreaTypeVO());
        result.add(new AreaTypeVO());
        return new Payload<>(new PageBean<>(result));
    }
}
