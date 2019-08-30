package com.deepexi.channel.controller;

import com.deepexi.channel.domain.store.StoreGradeDTO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.channel.domain.store.StoreGradeVO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.channel.domain.eo.CcStoreGrade;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.ArrayList;
import java.util.List;


@Api(value = "/门店等级管理", description = "门店等级管理")
@RestController
@RequestMapping("/api/v1/storeGrade")
public class StoreGradeController {

    @Autowired
    private StoreGradeService storeGradeService;

    @GetMapping
    @ApiOperation(value = "分页查询", notes = "查询门店等级列表,传-1时获取整个列表")
    public  Payload findPage(@ApiParam(name = "query", required = true) StoreGradeQuery query) {
        List<StoreGradeDTO> storeGradeDTOS = storeGradeService.findPage(query);
        if(CollectionUtil.isEmpty(storeGradeDTOS)){
            return new Payload(null);
        }
        List<StoreGradeVO> storeGradeVOS = ObjectCloneUtils.convertList(storeGradeDTOS,StoreGradeVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(new PageBean<>(storeGradeVOS));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询门店等级详情", notes = "查询门店等级详情")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(storeGradeService.detail(pk));

    }


    @PutMapping("/{id}")
    @Transactional
    @ApiOperation(value = "根据id修改", notes = "根据id修改门店等级")
    public Payload update(@PathVariable(value = "id", required = true) Long pk, @RequestBody StoreGradeVO vo) {
     vo.setId(pk);
     StoreGradeDTO storeGradeDTO = vo.clone(StoreGradeDTO.class);
     return new Payload(storeGradeService.update(storeGradeDTO));
    }

    @PostMapping
    @ApiOperation(value = "创建门店等级", notes = "创建门店等级")
    public Payload create(@RequestBody StoreGradeVO vo) {
        StoreGradeDTO storeGradeDTO = vo.clone(StoreGradeDTO.class);
        return new Payload(storeGradeService.create(storeGradeDTO));
    }

    @DeleteMapping("/{ids}")
    @Transactional
    @ApiOperation(value = "根据id批量删除CcStoreGrade", notes = "根据id批量删除CcStoreGrade")
    public Payload delete(@PathVariable(value = "ids", required = true) List<Long> ids) {
        return new Payload(storeGradeService.delete(ids));
    }

}