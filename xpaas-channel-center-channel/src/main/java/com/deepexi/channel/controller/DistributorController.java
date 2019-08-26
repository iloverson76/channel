package com.deepexi.channel.controller;

import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.distributor.DistributorQuery;
import com.deepexi.channel.domain.distributor.DistributorVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 经销商表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/distributor")
public class DistributorController {

    @PostMapping()
    @ApiOperation(value = "新增供应商")
    public Payload<Boolean> saveGrade(@RequestBody DistributorVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "删除供应商")
    public Payload<Boolean> deleteGrade(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "修改供应商")
    public Payload<Boolean> updateGrade(@RequestBody DistributorVO vo) {
        return new Payload<>(true);
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取供应商详情")
    public Payload<DistributorVO> getGradeById(@PathVariable Long id){
        return new Payload<>(new DistributorVO());
    }

    @GetMapping()
    @ApiOperation("查询供应商列表")
    public Payload<PageBean<DistributorVO>> listGradePage(@ApiParam(name = "query", required = true) DistributorQuery query){
        List<DistributorVO> result = new ArrayList<>();
        result.add(new DistributorVO());
        result.add(new DistributorVO());
        return new Payload<>(new PageBean<>(result));
    }


}
