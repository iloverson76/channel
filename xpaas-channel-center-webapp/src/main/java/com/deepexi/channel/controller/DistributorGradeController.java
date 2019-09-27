package com.deepexi.channel.controller;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.ForceDeleteEnum;
import com.deepexi.channel.service.DistributorGradeBusinessService;
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
    private DistributorGradeBusinessService distributorGradeBusinessService;

    @PostMapping
    @ApiOperation(value = "创建经销商等级")
    public Payload<Long> create(@RequestBody DistributorGradeBusiVO vo) {

        DistributorGradeBusiDTO dto=vo.clone(DistributorGradeBusiDTO.class, CloneDirection.FORWARD);

        Long result=distributorGradeBusinessService.create(dto);

        return new Payload(result);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查看详情")
    public Payload<DistributorGradeBusiVO> detail(@PathVariable(value = "id", required = true) long  gradeId) {

        DistributorGradeBusiDTO dto=distributorGradeBusinessService.detail(gradeId);

        DistributorGradeBusiVO vo=new DistributorGradeBusiVO();

        BeanUtils.copyProperties(dto,vo);

        DistributorGradeDTO parentDTO=dto.getParent();
        if(null!=parentDTO){

            DistributorGradeBusiVO parentVO=parentDTO.clone(DistributorGradeBusiVO.class,CloneDirection.OPPOSITE);

            vo.setParent(parentVO);
        }

        DistributorGradeSystemDTO systemDTO=dto.getSystem();
        if(null!=systemDTO){

            DistributorGradeSystemBusiVO systemVO=systemDTO.clone(DistributorGradeSystemBusiVO.class,CloneDirection.OPPOSITE);

            vo.setSystem(systemVO);
        }

        return new Payload<>(vo);
    }

    @PutMapping("/{id:[0-9,]+}")
    @Transactional
    @ApiOperation(value = "根据id修改经销商等级")
    public Payload<Boolean> update(@PathVariable(value = "id") Long id,@RequestBody DistributorGradeBusiVO vo) {

        vo.setId(id);

        DistributorGradeBusiDTO dto= vo.clone(DistributorGradeBusiDTO.class,CloneDirection.FORWARD);

        return new Payload<>(distributorGradeBusinessService.update(dto));
    }

    @DeleteMapping("/{id:[0-9,]+}")
    @ApiOperation(value = "根据id批量删除经销商等级")
    public Payload<Boolean> deleteBatchByIds(@PathVariable(value = "id") String ids) {

        List<Long> idList=Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        return new Payload<>(distributorGradeBusinessService.deleteBatchByIds(idList, ForceDeleteEnum.NO.getCode()));
    }

    @GetMapping
    @ApiOperation(value = "分页查询")
    public  Payload<PageBean<DistributorGradeBusiVO>> findPage(@ApiParam(name = "query", required = true) DistributorGradeQuery query) {

        List<DistributorGradeBusiDTO> dtoList= distributorGradeBusinessService.findPage(query);

        List<DistributorGradeBusiVO> voList=new ArrayList<>();

        if(CollectionUtils.isNotEmpty(dtoList)){

            dtoList.forEach(dto->{

                DistributorGradeSystemDTO systemDTO=dto.getSystem();

                DistributorGradeBusiVO vo= new DistributorGradeBusiVO();

                if(null!=systemDTO){

                    DistributorGradeSystemBusiVO systemVO=systemDTO.clone(DistributorGradeSystemBusiVO.class,CloneDirection.OPPOSITE);

                    BeanUtils.copyProperties(dto,vo);

                    vo.setSystem(systemVO);

                }else {
                    vo=dto.clone(DistributorGradeBusiVO.class,CloneDirection.OPPOSITE);
                }
                voList.add(vo);
            });
        }

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/parent/create/{systemId}")
    @ApiOperation(value = "查询可用的上级-新增用")
    public  Payload<PageBean<DistributorGradeBusiVO>> findParentNodesForCreat(@PathVariable(value = "systemId", required = true) Long  systemId) {

        List<DistributorGradeBusiDTO> dtoList= distributorGradeBusinessService.findParentNodesForCreate(systemId);

        List<DistributorGradeBusiVO> voList= ObjectCloneUtils.convertList(dtoList, DistributorGradeBusiVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/parent/update/{systemId}/{gradeId}")
    @ApiOperation(value = "查询可用的上级-修改用")
    public  Payload<PageBean<DistributorGradeBusiVO>> findParentNodesForUpdate(@PathVariable(value = "systemId", required = true) Long  systemId,
                                                                          @PathVariable(value = "gradeId", required = false)Long gradeId) {

        List<DistributorGradeBusiDTO> dtoList= distributorGradeBusinessService.findParentNodesForUpdate(systemId,gradeId);

        List<DistributorGradeBusiVO> voList= ObjectCloneUtils.convertList(dtoList, DistributorGradeBusiVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/system/{systemId}")
    @ApiOperation(value = "根据体系查询所有的等级")
    public  Payload<PageBean<DistributorGradeBusiVO>> listLinkedGrades(@PathVariable(value = "systemId")long systemId){

        List<DistributorGradeBusiDTO> dtoList=distributorGradeBusinessService.findAllGradesBySystem(systemId);

        List<DistributorGradeBusiVO> voList=new ArrayList<>();

        dtoList.forEach(dto->{

            DistributorGradeSystemBusiVO systemVO=dto.getSystem().clone(DistributorGradeSystemBusiVO.class,CloneDirection.FORWARD);

            if(systemVO!=null){
                DistributorGradeBusiVO vo= new DistributorGradeBusiVO();

                BeanUtils.copyProperties(dto,vo);

                vo.setSystem(systemVO);

                voList.add(vo);
            }else {
                voList.add(dto.clone(DistributorGradeBusiVO.class,CloneDirection.OPPOSITE));
            }
        });

        return new Payload<>(new PageBean<>(voList));
    }


}