package com.deepexi.channel.controller;

import com.deepexi.channel.domain.bank.BankVO;
import com.deepexi.util.config.Payload;
import io.swagger.annotations.ApiOperation;
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
    @GetMapping()
    @ApiOperation("查询连锁分类列表")
    private Payload<List<BankVO>> listChainPage(){
        return null;
    }
}
