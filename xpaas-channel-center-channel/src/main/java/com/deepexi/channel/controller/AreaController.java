package com.deepexi.channel.controller;

import com.deepexi.channel.businness.AreaBusinessService;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeVO;
import com.deepexi.channel.domain.area.AreaVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Api(description = "区域管理")
@RestController
@RequestMapping("/api/v1/area")
public class AreaController {

    @Autowired
    private AreaBusinessService areaBusinessService;

/*
    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcArea eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(areaService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcArea eo) {
        return new Payload(areaService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(areaService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcArea")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcArea eo) {
     eo.setId(pk);
     return new Payload(areaService.update(pk, eo));
    }
*/
/*
    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcArea", notes = "根据id删除CcArea")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(areaService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcArea", notes = "根据id批量删除CcArea")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(areaService.delete(ids));
    }
*/

    @PostMapping
    @ApiOperation(value = "创建区域")
    public Payload<Long> create(@RequestBody AreaVO vo) {

        Long result= areaBusinessService.create(vo.clone(AreaDTO.class, CloneDirection.FORWARD));

        return new Payload<>(result);
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
}