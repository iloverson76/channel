package com.deepexi.channel.controller;

import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeVO;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api(description = "经销商等级")
@RestController
@RequestMapping("/api/v1/distributorGrade")
public class DistributorGradeController {

    @Autowired
    private DistributorGradeService distributorGradeService;

    @PostMapping
    @ApiOperation(value = "创建经销商等级")
    public Payload<Long> create(@RequestBody DistributorGradeVO vo) {

        DistributorGradeDTO dto=vo.clone(DistributorGradeDTO.class, CloneDirection.FORWARD);

        Long result=distributorGradeService.create(dto);

        return new Payload(result);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查看详情")
    public Payload<DistributorGradeVO> detail(@PathVariable(value = "id", required = true) long  pk) {

        DistributorGradeVO vo=distributorGradeService.detail(pk).clone(DistributorGradeVO.class,CloneDirection.OPPOSITE);

        return new Payload<>(vo);
    }

    @PutMapping("/{id:[0-9]}")
    @Transactional
    @ApiOperation(value = "根据id修改经销商等级")
    public Payload<Boolean> update(@RequestBody DistributorGradeVO vo) {

        DistributorGradeDTO dto= vo.clone(DistributorGradeDTO.class,CloneDirection.FORWARD);

        return new Payload<>(distributorGradeService.update(dto));
    }

    @DeleteMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id批量删除经销商等级")
    public Payload<Boolean> delete(@PathVariable(value = "id") String ids) {

        List<Long> idList=Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        return new Payload<>(distributorGradeService.delete(idList));
    }

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload<PageBean<DistributorGradeVO>> findPage(@ApiParam(name = "query", required = true) DistributorGradeQuery query) {

        List<DistributorGradeDTO> dtoList=distributorGradeService.findPage(query);

        List<DistributorGradeVO> voList= ObjectCloneUtils.convertList(dtoList, DistributorGradeVO.class);;

        return new Payload<>(new PageBean<>(voList));
    }

}