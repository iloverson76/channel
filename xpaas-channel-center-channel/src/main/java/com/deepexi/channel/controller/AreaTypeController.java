package com.deepexi.channel.controller;


import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 区域类型表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/areaType")
public class AreaTypeController {

    @PostMapping()
    @ApiOperation(value = "新增区域分类")
    public Payload<Boolean> saveArea(@RequestBody AreaVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "删除区域分类")
    public Payload<Boolean> deleteArea(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "更新区域分类")
    public Payload<Boolean> updateArea(@RequestBody AreaVO vo) {
        return new Payload<>(true);
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取区域详情")
    public Payload<AreaVO> getChainById(@PathVariable Long id){
        return new Payload<>(new AreaVO());
    }


    @GetMapping()
    @ApiOperation("查询区域分类列表")
    public Payload<PageBean<AreaVO>> listChainPage(@ApiParam(name = "query", required = true) AreaQuery query){
        List<AreaVO> result = new ArrayList<>();
        result.add(new AreaVO());
        result.add(new AreaVO());
        return new Payload<>(new PageBean<>(result));
    }
}
