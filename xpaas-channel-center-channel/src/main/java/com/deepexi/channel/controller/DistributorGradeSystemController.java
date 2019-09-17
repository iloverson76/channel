package com.deepexi.channel.controller;

import com.deepexi.channel.businness.DistributorSystemBusinessService;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.domain.store.StoreDistributorDTO;
import com.deepexi.channel.domain.store.StoreDistributorVO;
import com.deepexi.channel.service.DistributorGradeSystemService;
import com.deepexi.util.CollectionUtil;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Api(description = "经销商体系")
@RestController
@RequestMapping("/api/v1/distributorGradeSystem")
public class DistributorGradeSystemController {

    @Autowired
    private DistributorGradeSystemService distributorGradeSystemService;

    @Autowired
    DistributorSystemBusinessService distributorSystemBusinessService;

    @PostMapping()
    @ApiOperation(value = "创建经销商体系")
    public Payload<Long> create(@RequestBody DistributorGradeSystemVO vo) {

        DistributorGradeSystemDTO dto=vo.clone(DistributorGradeSystemDTO.class, CloneDirection.FORWARD);

        distributorGradeSystemService.validateDuplicatedNameAndCode(dto);

        return new Payload(distributorGradeSystemService.create(dto));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查看详情")
    public Payload detail(@PathVariable(value = "id", required = true) long  pk) {

        DistributorGradeSystemDTO dto=distributorSystemBusinessService.detail(pk);

        DistributorGradeSystemVO vo=new DistributorGradeSystemVO();

        BeanUtils.copyProperties(dto,vo);

        if(null!=dto){

            List<DistributorGradeDTO> gradeDTOList=dto.getGrades();

            if(CollectionUtils.isNotEmpty(gradeDTOList)){

                List<DistributorGradeVO> gradeVOList=new ArrayList<>();

                if(CollectionUtils.isNotEmpty(gradeVOList)){

                    gradeDTOList.forEach(gradeDTO->{

                        DistributorGradeVO gradeVo=gradeDTO.clone(DistributorGradeVO.class,CloneDirection.OPPOSITE);

                        gradeVOList.add(gradeVo);
                    });
                }

            }
        }
        return new Payload<>(vo);
    }

    @PutMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id修改")
    public Payload<Boolean> update(@PathVariable(value = "id")long pk,@RequestBody DistributorGradeSystemVO vo) {

        vo.setId(pk);

        DistributorGradeSystemDTO dto=vo.clone(DistributorGradeSystemDTO.class,CloneDirection.FORWARD);

        return new Payload<>(distributorGradeSystemService.update(dto));
    }

    @DeleteMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id批量删除经销商体系")
    public Payload<Boolean> delete(@PathVariable(value = "id") String ids) {

        List<Long> idList= Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        return new Payload<>(distributorGradeSystemService.delete(idList));
    }

    @GetMapping
    @ApiOperation(value = "经销商体系列表-分页查询")
    public  Payload<PageBean<DistributorGradeSystemVO>> findPage(@ApiParam(name = "query", required = true) DistributorGradeSystemQuery query) {

        List<DistributorGradeSystemDTO> dtoList=distributorSystemBusinessService.findPage(query);

        List<DistributorGradeSystemVO> voList=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(dtoList)){

            dtoList.forEach(dto->{

                List<DistributorGradeDTO> grades=dto.getGrades();

                DistributorGradeSystemVO vo=new DistributorGradeSystemVO();

                if(CollectionUtils.isNotEmpty(grades)){

                    List<DistributorGradeVO> gradeVOList=
                            ObjectCloneUtils.convertList(grades,DistributorGradeVO.class,CloneDirection.OPPOSITE);

                    BeanUtils.copyProperties(dto,vo);

                    vo.setGrades(gradeVOList);

                }else{
                    vo=dto.clone(DistributorGradeSystemVO.class,CloneDirection.OPPOSITE);
                }
                voList.add(vo);
            });
        }

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/distributorGradeSystem/{distributorId}")
    @ApiOperation("门店关联经销商中，根据经销商id查询经销商体系列表")
    public  Payload<List<StoreDistributorVO>> getDistributorGradeSystemByDistributorId(@PathVariable(name = "distributorId", required = true) long distributorId){
        List<StoreDistributorDTO> dtos = distributorSystemBusinessService.getDistributorGradeSystemByDistributorId(distributorId);
        if (CollectionUtil.isEmpty(dtos)){
            return new Payload<>(Collections.emptyList());
        }
        return new Payload<>(ObjectCloneUtils.convertList(dtos, StoreDistributorVO.class));
    }
}