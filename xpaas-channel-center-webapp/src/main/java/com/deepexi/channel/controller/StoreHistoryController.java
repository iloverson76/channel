package com.deepexi.channel.controller;

import com.deepexi.channel.domain.StoreGradeVO;
import com.deepexi.channel.domain.StoreHistoryDetailDTO;
import com.deepexi.channel.domain.StoreHistoryDetailVO;
import com.deepexi.channel.service.StoreHistoryBusinessService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        StoreHistoryDetailDTO dto = storeHistoryBusinessService.detail(pk);
        if(dto == null){
            return new Payload<>();
        }
        StoreHistoryDetailVO vo = dto.clone(StoreHistoryDetailVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(vo);
    }
}