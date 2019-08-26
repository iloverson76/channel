package com.deepexi.channel.controller;

import com.deepexi.channel.domain.distributor.DistributorGradeSystemQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 等级体系-前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/distributorGradeSystem")
public class DistributorGradeSystemController {

    @PostMapping()
    @ApiOperation(value = "新增供应商等级体系")
    public Payload<Boolean> saveGradeSystem(@RequestBody DistributorGradeSystemVO vo) {
        return new Payload<>(true);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "删除供应商等级体系")
    public Payload<Boolean> deleteGradeSystem(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload<>(true);
    }

    @PutMapping()
    @ApiOperation(value = "修改供应商等级体系")
    public Payload<Boolean> updateGradeSystem(@RequestBody DistributorGradeSystemVO vo) {
        return new Payload<>(true);
    }

    @GetMapping("/{id:[0-9,]+}")
    @ApiOperation("根据id获取供应商等级体系详情")
    public Payload<DistributorGradeSystemVO> getGradeSystemById(@PathVariable Long id){
        return new Payload<>(new DistributorGradeSystemVO());
    }

    @GetMapping()
    @ApiOperation("查询供应商等级体系列表")
    public Payload<PageBean<DistributorGradeSystemVO>> listGradeSystemPage(@ApiParam(name = "query", required = true) DistributorGradeSystemQuery query){
        List<DistributorGradeSystemVO> result = new ArrayList<>();
        result.add(new DistributorGradeSystemVO());
        result.add(new DistributorGradeSystemVO());
        return new Payload<>(new PageBean<>(result));
    }
}
