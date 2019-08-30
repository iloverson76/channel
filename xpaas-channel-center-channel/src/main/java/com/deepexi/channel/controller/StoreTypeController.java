package com.deepexi.channel.controller;

import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.domain.store.StoreTypeVO;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.channel.domain.eo.CcStoreType;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.List;


@Api(value = "/门店类型管理", description = "门店类型管理")
@RestController
@RequestMapping("/api/v1/storeType")
public class StoreTypeController {

    @Autowired
    private StoreTypeService storeTypeService;


    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求,page传-1时获取所有门店类型")
    public  Payload findPage(@ApiParam(name = "query", required = true) StoreTypeQuery query) {
//        return new Payload(storeTypeService.findPage(eo, page, size));
//        List<StoreTypeVO> result = new ArrayList<>();
//        result.add(new StoreTypeVO());
//        result.add(new StoreTypeVO());
        List<StoreTypeDTO> storeTypeDTOS = storeTypeService.findPage(query);
        return new Payload<>(new PageBean<>(storeTypeDTOS));
    }

//    @GetMapping("/list")
//    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
//    public Payload findAll(CcStoreType eo) {
//        return new Payload(storeTypeService.findAll(eo));
//    }
//
    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  id) {
        StoreTypeDTO storeTypeDTO = storeTypeService.detail(id);
        if(storeTypeDTO == null){
            return new Payload();
        }
        return new Payload(storeTypeDTO.clone(StoreTypeVO.class, CloneDirection.OPPOSITE));
    }


    @PutMapping("/{id}")
    @Transactional
    @ApiOperation(value = "根据id修改", notes = "根据id修改门店类型")
    public Payload update(@PathVariable(value = "id", required = true) Long id, @RequestBody StoreTypeVO vo) {
         vo.setId(id);
         return new Payload(storeTypeService.update(vo.clone(StoreTypeDTO.class)));
    }

    @PostMapping
    @ApiOperation(value = "创建门店类型", notes = "创建门店类型")
    public Payload create(@RequestBody StoreTypeVO vo) {
        return new Payload<>(storeTypeService.create(vo.clone(StoreTypeDTO.class)));
    }

    @DeleteMapping("/{ids}")
    @Transactional
    @ApiOperation(value = "根据id批量删除门店类型", notes = "根据id批量删除门店类型")
    public Payload delete(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload(storeTypeService.delete(ids));
    }

}