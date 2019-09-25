package com.deepexi.channel.controller;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.service.StoreHistoryBusinessService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/24 18:18
 */
@Api(value = "/门店历史信息表",description = "门店历史信息管理")
@RestController
@RequestMapping("/api/v1/storeHistory")
public class StoreHistoryController {

    @Autowired
    private StoreHistoryBusinessService storeHistoryBusinessService;

    @GetMapping("/{id}")
    @ApiOperation(value = "查询门店等级详情", notes = "查询门店等级详情")
    public Payload<StoreHistoryDetailVO> detail(@PathVariable(value = "id", required = true) Long pk) {
        StoreHistoryDTO dto = storeHistoryBusinessService.detail(pk);
        if(dto == null){
            return new Payload<>();
        }
        StoreHistoryDetailVO vo = dto.clone(StoreHistoryDetailVO.class);
        //拼接数据
        if(StringUtil.isNotEmpty(dto.getArea())){
            vo.setAreaBusiVOS(JsonUtil.json2Bean(dto.getArea(),List.class));
        }
        if(StringUtil.isNotEmpty(dto.getChain())){
            vo.setChainVOS(JsonUtil.json2Bean(dto.getChain(),List.class));
        }
        if(StringUtil.isNotEmpty(dto.getStoreDistributor())){
            vo.setStoreDistributorVOS(JsonUtil.json2Bean(dto.getStoreDistributor(),List.class));
        }
        if(StringUtil.isNotEmpty(dto.getStoreType())){
            vo.setStoreTypeVO(JsonUtil.json2Bean(dto.getStoreType(), StoreTypeVO.class));
        }
        if(StringUtil.isNotEmpty(dto.getStoreGrade())){
            vo.setStoreGradeVO(JsonUtil.json2Bean(dto.getStoreGrade(), StoreGradeVO.class));
        }

        return new Payload<>(vo);
    }
}