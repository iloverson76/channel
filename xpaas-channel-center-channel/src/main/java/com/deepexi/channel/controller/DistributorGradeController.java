package com.deepexi.channel.controller;

import com.deepexi.channel.businness.DistributorGradeBusinessService;
import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemVO;
import com.deepexi.channel.domain.distributor.DistributorGradeVO;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api(description = "经销商等级")
@RestController
@RequestMapping("/api/v1/distributorGrade")
public class DistributorGradeController {

    @Autowired
    private DistributorGradeService distributorGradeService;

    @Autowired
    DistributorGradeBusinessService distributorGradeBusinessService;

    @PostMapping
    @ApiOperation(value = "创建经销商等级")
    public Payload<Long> create(@RequestBody DistributorGradeVO vo) {

        distributorGradeService.validateGradeCode(vo.getDistributorGradeCode());

        DistributorGradeDTO dto=vo.clone(DistributorGradeDTO.class, CloneDirection.FORWARD);

        Long result=distributorGradeService.create(dto);

        return new Payload(result);
    }

    @GetMapping("/{id}/{systemId}")
    @ApiOperation(value = "根据id查看详情")
    public Payload<DistributorGradeVO> detail(@PathVariable(value = "id", required = true) long  gradeId,
                                              @PathVariable(value = "systemId", required = true) long  systemId) {

        DistributorGradeVO vo=distributorGradeBusinessService.detail(gradeId,systemId).clone(DistributorGradeVO.class,CloneDirection.OPPOSITE);

        return new Payload<>(vo);
    }

    @PutMapping("/{id:[0-9,]+}")
    @Transactional
    @ApiOperation(value = "根据id修改经销商等级")
    public Payload<Boolean> update(@PathVariable(value = "id") Long id,@RequestBody DistributorGradeVO vo) {

        vo.setId(id);

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
    @ApiOperation(value = "分页查询")
    public  Payload<PageBean<DistributorGradeVO>> findPage(@ApiParam(name = "query", required = true) DistributorGradeQuery query) {

        List<DistributorGradeDTO> dtoList= distributorGradeBusinessService.findPage(query);

        List<DistributorGradeVO> voList=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(dtoList)){

            dtoList.forEach(dto->{

                DistributorGradeSystemVO systemVO=dto.getSystem().clone(DistributorGradeSystemVO.class,CloneDirection.OPPOSITE);

                DistributorGradeVO vo= new DistributorGradeVO();

                BeanUtils.copyProperties(dto,vo);

                vo.setSystem(systemVO);

                voList.add(vo);
            });
        }

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/parent/{systemId}")
    @ApiOperation(value = "上级接口-新增用")
    public  Payload<PageBean<DistributorGradeVO>> findParentNodesForCreat(@PathVariable(value = "systemId", required = true) long  systemId) {

        List<DistributorGradeDTO> dtoList= distributorGradeBusinessService.findParentNodesForCreat(systemId);

        List<DistributorGradeVO> voList= ObjectCloneUtils.convertList(dtoList, DistributorGradeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

}