package com.deepexi.channel.controller;

import com.deepexi.channel.businness.AreaBusinessService;
import com.deepexi.channel.businness.AreaTypeBusinessService;
import com.deepexi.channel.domain.area.*;
import com.deepexi.channel.service.AreaService;
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
import java.util.List;
import java.util.stream.Collectors;


@Api(description = "区域管理")
@RestController
@RequestMapping("/api/v1/area")
public class AreaController {

    @Autowired
    private AreaBusinessService areaBusinessService;

    @Autowired
    private AreaService areaService;

    @Autowired
    AreaTypeBusinessService areaTypeBusinessService;

    @PostMapping
    @ApiOperation(value = "创建区域")
    public Payload<Long> create(@RequestBody AreaVO vo) {

        Long result= areaBusinessService.create(vo.clone(AreaDTO.class, CloneDirection.FORWARD));

        return new Payload<>(result);
    }

    @GetMapping("/{id}/{areaTypeId}")
    @ApiOperation(value="根据id和分类id查看区域详情")
    public Payload<AreaVO> detail(@PathVariable(value = "id", required = true) Long  id,
                                   @PathVariable(value = "areaTypeId", required = true)Long areaTypeId) {

        AreaDTO dto=areaBusinessService.detail(id,areaTypeId);

        AreaTypeVO type=dto.getAreaType().clone(AreaTypeVO.class,CloneDirection.OPPOSITE);

        AreaVO vo=new AreaVO();

        BeanUtils.copyProperties(dto,vo);

        vo.setAreaType(type);

        return new Payload<>(vo);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long  pk, @RequestBody AreaVO vo) {

        vo.setId(pk);

        areaBusinessService.update(vo.clone(AreaDTO.class,CloneDirection.FORWARD));

        return new Payload<>(Boolean.TRUE);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "根据id批量删除")
    public Payload delete(@PathVariable(value = "ids") String ids) {

        List<Long> idList= Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        return new Payload(areaBusinessService.deleteBatch(idList));
    }

    @GetMapping()
    @ApiOperation("查询区域列表-分页查询")
    public Payload<PageBean<AreaVO>> listAreaPage(@ApiParam(name = "query", required = true) AreaQuery query) {

        List<AreaDTO> dtoList = areaBusinessService.findPage(query);

        List<AreaVO> voList=new ArrayList<>();

        dtoList.forEach(dto->{

            AreaTypeVO areaTypeVO=dto.getAreaType().clone(AreaTypeVO.class,CloneDirection.OPPOSITE);

            AreaVO vo= new AreaVO();

            BeanUtils.copyProperties(dto,vo);

            vo.setAreaType(areaTypeVO);

            voList.add(vo);
        });

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/tree")
    @ApiOperation("区域树")
    public Payload<PageBean<AreaTreeVO>> listAreaTree(@ApiParam(name = "query", required = true) AreaTreeQuery query) {

        List<AreaTreeDTO> dtoList = areaBusinessService.buildAreaTree(query);

        return tree( dtoList);
    }

    @GetMapping("/treeEdit/{parentId}/{id}/{root}")
    @ApiOperation("区域树修改保存")
    public Payload<Boolean> editTree(@PathVariable(name = "parentId") Long parentId,
                                                  @PathVariable(name = "id", required = true) Long id,
                                                  @PathVariable(name = "root", required = true) Integer root) {

        areaBusinessService.updateTreeChange(parentId,id,root);

        return new Payload<>( Boolean.TRUE);
    }

    @GetMapping("/childrenTree/{areaId}")
    @ApiOperation("根据区域ID查找所有下级树")
    public Payload<PageBean<AreaTreeVO>> listChildrenTree(@PathVariable(name = "areaId", required = true) Long areaId) {

        List<AreaTreeDTO> dtoList = areaBusinessService.listChildrenTree(areaId);

        return tree( dtoList);

    }

    private Payload<PageBean<AreaTreeVO>>  tree(List<AreaTreeDTO> dtoList){

        List<AreaTreeVO> voList= new ArrayList<>();

        if(CollectionUtils.isNotEmpty(dtoList)){

            dtoList.forEach(dto->{

                AreaTypeVO areaTypeVO=dto.getAreaType().clone(AreaTypeVO.class,CloneDirection.OPPOSITE);

                AreaTreeVO vo= new AreaTreeVO();

                BeanUtils.copyProperties(dto,vo);

                vo.setAreaType(areaTypeVO);

                voList.add(vo);
            });
        }
        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/ParentAreaType/{areaId}")
    @ApiOperation("根据区域ID查找其上级分类")
    public Payload<PageBean<AreaTypeVO>> findParentAreaTypeByAreaId(@PathVariable(value = "areaId", required = true) Long areaId) {

        List<AreaTypeDTO> dtoList = areaTypeBusinessService.findParentAreaTypeByAreaId(areaId);

        List<AreaTypeVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/linkedAreas/{areaTypeId}")
    @ApiOperation("根据区域类型ID查找其挂载的所有区域")
    public Payload<PageBean<AreaDTO>> listLinkedAreasByType(@PathVariable(name = "areaTypeId", required = true) Long areaTypeId) {

        List<AreaDTO> dtoList = areaBusinessService.listLinkedAreasByType(areaTypeId);

        return new Payload<>(new PageBean<>(ObjectCloneUtils.convertList(dtoList,AreaDTO.class,CloneDirection.FORWARD)));
    }

}