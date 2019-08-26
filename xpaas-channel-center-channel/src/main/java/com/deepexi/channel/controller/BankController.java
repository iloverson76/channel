package com.deepexi.channel.controller;

import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.bank.BankVO;
import com.deepexi.channel.service.IBankService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 银行账户表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2019-08-23
 */
@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    @Autowired
    IBankService iBankService;

    @GetMapping()
    @ApiOperation("查询连锁分类列表")
    private Payload<List<BankVO>> listChainPage(){
        List<BankDTO> bankDTOS = iBankService.listBank();
        return new Payload<>(ObjectCloneUtils.convertList(bankDTOS, BankVO.class, CloneDirection.OPPOSITE));
    }
}
