package com.deepexi.channel.controller;

import com.deepexi.channel.businness.StoreTypeBusinessService;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.domain.store.StoreTypeVO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.channel.domain.eo.CcStoreType;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "/门店类型管理", description = "门店类型管理")
@RestController
@RequestMapping("/api/v1/storeType")
public class StoreTypeController {

    @Autowired
    private StoreTypeService storeTypeService;
    @Autowired
    private StoreTypeBusinessService storeTypeBusinessService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求,page传-1时获取所有门店类型")
    public  Payload<PageBean<StoreTypeVO>> findPage(@ApiParam(name = "query", required = true) StoreTypeQuery query) {
        List<StoreTypeDTO> storeTypeDTOS = storeTypeService.findPage(query);
        if(CollectionUtil.isEmpty(storeTypeDTOS)){
            return new Payload(null);
        }
        List<StoreTypeVO> storeTypeVOS = ObjectCloneUtils.convertList(storeTypeDTOS, StoreTypeVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(new PageBean<>(storeTypeVOS));
    }

//    @GetMapping("/list")
//    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
//    public Payload findAll(CcStoreType eo) {
//        return new Payload(storeTypeService.findAll(eo));
//    }
//
    @GetMapping("/{id}")
    public Payload<StoreTypeVO> detail(@PathVariable(value = "id", required = true) Integer  id) {
        StoreTypeDTO storeTypeDTO = storeTypeService.detail(id);
        if(storeTypeDTO == null){
            return new Payload();
        }
        return new Payload(storeTypeDTO.clone(StoreTypeVO.class, CloneDirection.OPPOSITE));
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改", notes = "根据id修改门店类型")
    public Payload update(@PathVariable(value = "id", required = true) Long id, @RequestBody StoreTypeVO vo) {
         vo.setId(id);
         return new Payload(storeTypeService.update(vo.clone(StoreTypeDTO.class)));
    }

    @PostMapping
    @ApiOperation(value = "创建门店类型", notes = "创建门店类型")
    public Payload<Long> create(@RequestBody StoreTypeVO vo) {
        return new Payload<>(storeTypeService.create(vo.clone(StoreTypeDTO.class)));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据id批量删除门店类型", notes = "根据id批量删除门店类型")
    public Payload delete(@PathVariable(value = "id", required = true) String id) {
        List<Long> ids = Arrays.stream(id.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return new Payload(storeTypeBusinessService.deleteStoreType(ids));
    }

}