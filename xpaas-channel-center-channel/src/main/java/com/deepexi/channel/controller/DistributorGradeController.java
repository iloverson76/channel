package com.deepexi.channel.controller;

import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 经销商等级表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/distributorGrade")
public class DistributorGradeController {

    @PostMapping()
    @ApiOperation(value = "新增供应商等级")
    public Payload<Boolean> saveGrade(@RequestBody DistributorGradeVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "删除供应商等级")
    public Payload<Boolean> deleteGrade(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "修改供应商等级")
    public Payload<Boolean> updateGrade(@RequestBody DistributorGradeVO vo) {
        return new Payload<>(true);
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取供应商等级详情")
    public Payload<DistributorGradeVO> getGradeById(@PathVariable Long id){
        return new Payload<>(new DistributorGradeVO());
    }

    @GetMapping()
    @ApiOperation("查询等级供应商列表")
    public Payload<PageBean<DistributorGradeVO>> listGradePage(@ApiParam(name = "query", required = true) DistributorGradeQuery query){
        List<DistributorGradeVO> result = new ArrayList<>();
        result.add(new DistributorGradeVO());
        result.add(new DistributorGradeVO());
        return new Payload<>(new PageBean<>(result));
    }
}
