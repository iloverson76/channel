package com.deepexi.channel.controller;

import com.deepexi.channel.domain.distributor.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemVO;
import com.deepexi.channel.service.DistributorGradeSystemService;
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
import java.util.stream.Collectors;

@Api(description = "经销商体系")
@RestController
@RequestMapping("/api/v1/distributorGradeSystem")
public class DistributorGradeSystemController {

    @Autowired
    private DistributorGradeSystemService distributorGradeSystemService;

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查看详情")
    public Payload detail(@PathVariable(value = "id", required = true) long  pk) {

        DistributorGradeSystemVO vo=distributorGradeSystemService.detail(pk).clone(DistributorGradeSystemVO.class,CloneDirection.OPPOSITE);

        return new Payload<>(vo);

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改")
    public Payload<Boolean> update(@RequestBody DistributorGradeSystemVO vo) {

        DistributorGradeSystemDTO dto=vo.clone(DistributorGradeSystemDTO.class,CloneDirection.FORWARD);

        return new Payload<>(distributorGradeSystemService.update(dto));
    }

    @PostMapping
    @ApiOperation(value = "创建经销商体系")
    public Payload<Long> create(@RequestBody DistributorGradeSystemVO vo) {

        DistributorGradeSystemDTO dto=vo.clone(DistributorGradeSystemDTO.class, CloneDirection.FORWARD);

        distributorGradeSystemService.validateDuplicatedNameAndCode(dto);

        return new Payload(distributorGradeSystemService.create(dto));
    }

    @DeleteMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id批量删除经销商体系")
    public Payload<Boolean> delete(@PathVariable(value = "id") String ids) {

        List<Long> idList= Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        return new Payload<>(distributorGradeSystemService.delete(idList));
    }

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload<PageBean<DistributorGradeSystemVO>> findPage(@ApiParam(name = "query", required = true) DistributorGradeSystemQuery query) {

        List<DistributorGradeSystemDTO> dtoList=distributorGradeSystemService.findPage(query);

        List<DistributorGradeSystemVO> voList= ObjectCloneUtils.convertList(dtoList, DistributorGradeSystemVO.class);;

        return new Payload<>(new PageBean<>(voList));
    }

}