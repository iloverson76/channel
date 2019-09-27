package com.deepexi.channel.controller;


import com.deepexi.channel.domain.*;
import com.deepexi.channel.enums.ForceDeleteEnum;
import com.deepexi.channel.service.AreaTypeBusinessService;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 区域类型-前端控制器
 * </p>
 *
 * @author chp
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/areaType")
@Api(description="区域类型管理")
public class AreaTypeController {

    @Autowired
    AreaTypeService areaTypeService;

    @Autowired
    AreaTypeBusinessService areaTypeBusinessService;

    @PostMapping()
    @ApiOperation(value = "新增区域类型")
    public Payload<Long> saveAreaType(@RequestBody AreaTypeBusiVO vo) {

        AreaTypeBusiDTO dto = vo.clone(AreaTypeBusiDTO.class, CloneDirection.FORWARD);

        Long newId=areaTypeBusinessService.createAreaType(dto);

        return new Payload<>(newId);
    }

    @DeleteMapping("/{id:[0-9,]}")
    @ApiOperation(value = "单条删除区域类型")
    public Payload<Boolean> deleteAreaTypeByIds(@PathVariable(value = "id") Long id) {//前端的列表每一个id都去拿子节点的话,性能很慢

        Boolean result = areaTypeBusinessService.deleteAreaTypeById(id);

        return new Payload<>(result);
    }

    @DeleteMapping("/{ids:[0-9,]+}")
    @ApiOperation(value = "批量删除区域类型")
    public Payload<Boolean> deleteAreaTypeByIds(@PathVariable(value = "ids") String ids) {//前端的列表每一个id都去拿子节点的话,性能很慢

        List<Long> idList=Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        Boolean result = areaTypeBusinessService.deleteAreaTypeByIds(idList, ForceDeleteEnum.NO.getCode());

        return new Payload<>(result);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改区域类型")
    public Payload<Boolean> updateAreaTypeById(@PathVariable(value = "id", required = true) Long pk,@RequestBody AreaTypeBusiVO vo) {

        vo.setId(pk);

        AreaTypeBusiDTO dto = vo.clone(AreaTypeBusiDTO.class, CloneDirection.FORWARD);

        boolean result = areaTypeBusinessService.update(dto);

        return new Payload<>(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取区域分类详情")
    public Payload<AreaTypeBusiVO> getAreaTypeById(@PathVariable Long id) {

        AreaTypeBusiDTO dto = areaTypeBusinessService.detail(id);

        AreaTypeBusiVO vo = dto.clone(AreaTypeBusiVO.class, CloneDirection.OPPOSITE);

        return new Payload<>(vo);
    }

    @GetMapping()
    @ApiOperation("查询区域类型列表")
    public Payload<PageBean<AreaTypeBusiVO>> listAreaTypePage(@ApiParam(name = "query", required = true) AreaTypeQuery query) {

        List<AreaTypeBusiDTO> dtoList = areaTypeBusinessService.findPage(query);

        List<AreaTypeBusiVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeBusiVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/limitedCreate")
    @ApiOperation("查询未受分类限制上级-新增用")
    public Payload<PageBean<AreaTypeBusiVO>> listParentForCreate() {

        List<AreaTypeBusiDTO> dtoList = areaTypeBusinessService.listParentNodesForCreate();

        List<AreaTypeBusiVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeBusiVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/limitedUpdaTe/{id:[0-9,]+}")
    @ApiOperation("查询未受分类限制上级-更新用")
    public Payload<PageBean<AreaTypeBusiVO>> listParentForUpdate(@PathVariable Long id) {

        List<AreaTypeBusiDTO> dtoList = areaTypeBusinessService.listParentNodesForUpdate(id);

        List<AreaTypeBusiVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeBusiVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/typeArea/{id:[0-9,]+}")
    @ApiOperation("层级元素列表")
    public Payload<PageBean<AreaBusiVO>> listTypeAreas(@PathVariable Long id) {

        List<AreaBusiDTO> dtoList = areaTypeBusinessService.listLinkedAreas(id);

        List<AreaBusiVO> voList=new ArrayList<>();

        if(CollectionUtil.isEmpty(dtoList)){
            return new Payload<>();
        }

        dtoList.forEach(dto->{

            AreaTypeBusiVO areaTypeBusiVO=dto.getAreaType().clone(AreaTypeBusiVO.class,CloneDirection.OPPOSITE);

            AreaBusiVO vo= new AreaBusiVO();

            BeanUtils.copyProperties(dto,vo);

            vo.setAreaType(areaTypeBusiVO);

            voList.add(vo);
        });

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/linkIdNotIn")
    @ApiOperation("查询是否已经关联")
    public Payload<AreaTypeListLinkVO> getListChainType(@RequestParam String linkIds){
        List<Long> ids = new ArrayList<>();
        if (StringUtil.isNotEmpty(linkIds)){
            ids = Arrays.stream(linkIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
        }
        List<AreaTypeBusiDTO> listAreaType = areaTypeBusinessService.getListAreaType(ids);
        List<AreaTypeBusiVO> areaTypeBusiVOList = ObjectCloneUtils.convertList(listAreaType, AreaTypeBusiVO.class, CloneDirection.OPPOSITE);
        if (CollectionUtil.isEmpty(areaTypeBusiVOList)){
            return new Payload<>();
        }
        AreaTypeListLinkVO vo = new AreaTypeListLinkVO();
        Set<Long> setMap = areaTypeBusiVOList.stream().filter(s->null!=s.getLinkId()).map(AreaTypeBusiVO::getLinkId).collect(Collectors.toSet());
        vo.setLinkType(setMap.size());
        vo.setAreaType(areaTypeBusiVOList);
        return new Payload<>(vo);
    }

}