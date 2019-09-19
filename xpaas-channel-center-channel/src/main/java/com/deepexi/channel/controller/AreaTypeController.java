package com.deepexi.channel.controller;


import com.deepexi.channel.businness.AreaTypeBusinessService;
import com.deepexi.channel.domain.area.*;
import com.deepexi.channel.extension.AppRuntimeEnv;
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

    AppRuntimeEnv appRuntimeEnv = AppRuntimeEnv.getInstance();

    @PostMapping()
    @ApiOperation(value = "新增区域类型")
    public Payload<Long> saveAreaType(@RequestBody AreaTypeVO vo) {

        AreaTypeDTO dto = vo.clone(AreaTypeDTO.class, CloneDirection.FORWARD);

        Long result = areaTypeService.saveAreaType(dto);

        return new Payload<>(result);
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

        List<Long> idSet=Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());

        Boolean result = areaTypeBusinessService.deleteAreaTypeByIds(idSet);

        return new Payload<>(result);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "修改区域类型")
    public Payload<Boolean> updateAreaTypeById(@PathVariable(value = "id", required = true) Long pk,@RequestBody AreaTypeVO vo) {

        vo.setId(pk);

        AreaTypeDTO dto = vo.clone(AreaTypeDTO.class, CloneDirection.FORWARD);

        boolean result = areaTypeBusinessService.update(dto);

        return new Payload<>(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取类目详情")
    public Payload<AreaTypeVO> getAreaTypeById(@PathVariable Long id) {

        AreaTypeDTO dto = areaTypeService.getAreaTypeById(id);

        AreaTypeVO vo = dto.clone(AreaTypeVO.class, CloneDirection.OPPOSITE);

        return new Payload<>(vo);
    }

    @GetMapping()
    @ApiOperation("查询区域类型列表")
    public Payload<PageBean<AreaTypeVO>> listAreaTypePage(@ApiParam(name = "query", required = true) AreaTypeQuery query) {

        List<AreaTypeDTO> dtoList = areaTypeBusinessService.findPage(query);

        List<AreaTypeVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/limitedCreate")
    @ApiOperation("查询未受分类限制上级-新增用")
    public Payload<PageBean<AreaTypeVO>> listParentForCreate() {

        List<AreaTypeDTO> dtoList = areaTypeBusinessService.listParentNodesForCreate();

        List<AreaTypeVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/limitedUpdaTe/{id:[0-9,]+}")
    @ApiOperation("查询未受分类限制上级-更新用")
    public Payload<PageBean<AreaTypeVO>> listParentForUpdate(@PathVariable Long id) {

        List<AreaTypeDTO> dtoList = areaTypeBusinessService.listParentNodesForUpdate(id);

        List<AreaTypeVO> voList = ObjectCloneUtils.convertList(dtoList, AreaTypeVO.class);

        return new Payload<>(new PageBean<>(voList));
    }

    @GetMapping("/typeArea/{id:[0-9,]+}")
    @ApiOperation("层级元素列表")
    public Payload<PageBean<AreaVO>> listTypeAreas(@PathVariable Long id) {

        List<AreaDTO> dtoList = areaTypeBusinessService.listLinkedAreas(id);

        List<AreaVO> voList=new ArrayList<>();

        if(CollectionUtil.isEmpty(dtoList)){
            return new Payload<>();
        }

        dtoList.forEach(dto->{

            AreaTypeVO areaTypeVO=dto.getAreaType().clone(AreaTypeVO.class,CloneDirection.OPPOSITE);

            AreaVO vo= new AreaVO();

            BeanUtils.copyProperties(dto,vo);

            vo.setAreaType(areaTypeVO);

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
        List<AreaTypeDTO> listAreaType = areaTypeBusinessService.getListAreaType(ids);
        List<AreaTypeVO> areaTypeVOList = ObjectCloneUtils.convertList(listAreaType, AreaTypeVO.class, CloneDirection.OPPOSITE);
        if (CollectionUtil.isEmpty(areaTypeVOList)){
            return new Payload<>();
        }
        AreaTypeListLinkVO vo = new AreaTypeListLinkVO();
        Set<Long> setMap = areaTypeVOList.stream().filter(s->null!=s.getLinkId()).map(AreaTypeVO::getLinkId).collect(Collectors.toSet());
        vo.setLinkType(setMap.size());
        vo.setAreaType(areaTypeVOList);
        return new Payload<>(vo);
    }

}