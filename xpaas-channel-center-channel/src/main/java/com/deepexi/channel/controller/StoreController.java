package com.deepexi.channel.controller;

import com.deepexi.channel.businness.StoreBusinessService;
import com.deepexi.channel.domain.store.*;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.StoreService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.extension.ApplicationException;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Api(value = "/门店信息表", description = "查询门店列表")
@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private StoreBusinessService storeBusinessService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "分页请求")
    public Payload<PageBean<StoreVO>> findPage(@ApiParam(name = "query", required = true) StoreQuery query) {
        return new Payload(new PageBean<>(storeService.findPage(query)));
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id获取门店详情")
    public Payload<StoreDetailVO> detail(@PathVariable(value = "id", required = true) Long  pk) {
        return new Payload(storeBusinessService.detail(pk));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改", notes = "根据id修改CcStore")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long pk, @RequestBody StoreDetailVO vo) {
        vo.setId(pk);
        StoreDetailDTO storeDetailDTO = vo.clone(StoreDetailDTO.class);
        //编码是否重复
        if(!storeService.isCodeUnique(storeDetailDTO)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        return new Payload(storeBusinessService.update(storeDetailDTO));
    }

    @PostMapping
    @ApiOperation(value = "创建门店", notes = "创建门店")
    public Payload<Long> create(@RequestBody StoreDetailVO vo) {
        StoreDetailDTO storeDetailDTO = vo.clone(StoreDetailDTO.class);
        if(vo.getStoreGradeVO() != null){
            StoreGradeDTO storeGradeDTO = vo.getStoreGradeVO().clone(StoreGradeDTO.class);
            storeDetailDTO.setStoreGradeDTO(storeGradeDTO);
        }
       if(vo.getStoreTypeVO() != null){
           StoreTypeDTO storeTypeDTO = vo.getStoreTypeVO().clone(StoreTypeDTO.class);
           storeDetailDTO.setStoreTypeDTO(storeTypeDTO);
       }

        //编码是否重复
        if(!storeService.isCodeUnique(storeDetailDTO)){
            throw new ApplicationException(ResultEnum.CODE_NOT_UNIQUE);
        }
        return new Payload(storeBusinessService.create(storeDetailDTO));
    }

    @DeleteMapping("/{id:[a-zA-Z0-9,]+}")
    @ApiOperation(value = "根据id批量删除门店", notes = "根据id批量删除store")
    public Payload<Boolean> delete(@PathVariable(value = "id", required = true) String id) {
        List<Long> ids = Arrays.stream(id.split(",")).map(Long::parseLong).collect(Collectors.toList());
        return new Payload(storeService.delete(ids));
    }

}