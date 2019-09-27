package com.deepexi.channel.controller;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.DistributorTypeEnum;
import com.deepexi.channel.enums.ForceDeleteEnum;
import com.deepexi.channel.service.AreaService;
import com.deepexi.channel.service.DistributorService;
import com.deepexi.channel.service.DistributorBusinessService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(description = "经销商管理")
@RestController
@RequestMapping("/api/v1/distributor")
public class DistributorController {

    @Autowired
    private DistributorBusinessService distributorBusinessService;

    @PostMapping
    @ApiOperation(value = "创建经销商")
    public Payload<Boolean> create(@RequestBody DistributorBusiVO vo) {

        return new Payload(distributorBusinessService.create(vo.clone(DistributorBusiDTO.class,CloneDirection.OPPOSITE)));
    }

    @DeleteMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id批量删除经销商")
    public Payload<Boolean> delete(@PathVariable(value = "id") String ids) {

        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        return new Payload<>(distributorBusinessService.deleteBatchByIds(idList, ForceDeleteEnum.NO.getCode()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查看经销商详情")
    public Payload<DistributorBusiVO> detail(@PathVariable(value = "id", required = true) Long id) {

        DistributorBusiDTO dto = distributorBusinessService.detail(id);

        DistributorBusiVO vo=dto.clone(DistributorBusiVO.class,CloneDirection.OPPOSITE);

        return new Payload<>(vo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long pk, @RequestBody DistributorBusiVO vo) {

        vo.setId(pk);

        distributorBusinessService.update(vo.clone(DistributorBusiDTO.class, CloneDirection.FORWARD));

        return new Payload<>(Boolean.TRUE);
    }

    @GetMapping()
    @ApiOperation("查询经销商列表-分页查询")
    public Payload<PageBean<DistributorBusiVO>> listAreaPage(@ApiParam(name = "query", required = true) DistributorQuery query) {

        List<DistributorBusiDTO> dtoList = distributorBusinessService.findPage(query);

        List<DistributorBusiVO> voList = ObjectCloneUtils.convertList(dtoList,DistributorBusiVO.class,CloneDirection.FORWARD);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/distributorType")
    @ApiOperation("经销商类型-下拉框")
    public Payload<PageBean<Map<String,String>>> listDistributorTypes(@ApiParam(name = "query", required = true) DistributorQuery query) {

        List<Map<String,String>> typeList=DistributorTypeEnum.getTypeList();

        return new Payload<>(new PageBean<>(typeList));
    }

    @GetMapping("/parent/{gradeId}")
    @ApiOperation("按等级查询上级经销商")
    public Payload<PageBean<DistributorBusiVO>> listParentDistributorsByGrade(
            @PathVariable(name = "gradeId", required = true) long gradeId) {

        List<DistributorBusiDTO> dtoList=
        distributorBusinessService.listParentDistributorsByGrade(gradeId);

        List<DistributorBusiVO> voList= ObjectCloneUtils.convertList(dtoList,DistributorBusiVO.class,
                CloneDirection.OPPOSITE);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/gradeInfo/{distributorId}")
    @ApiOperation("根据经销商查询等级信息列表")
    public Payload<PageBean<GradeInfoVO>> listDistributorGradeInfo(
            @PathVariable(name = "distributorId", required = true) long distributorId) {

        List<GradeInfoDTO> dtoList=
                distributorBusinessService.getGradeInfo(distributorId);

        List<GradeInfoVO> voList= ObjectCloneUtils.convertList(dtoList,GradeInfoVO.class,
                CloneDirection.OPPOSITE);

        return new Payload<>(new PageBean<>(voList));
    }



}


