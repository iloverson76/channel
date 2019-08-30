package com.deepexi.channel.controller;

import com.deepexi.channel.domain.bank.BankDO;
import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.bank.BankVO;
import com.deepexi.util.config.Payload;
//import io.swagger.annotations.Api;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.BankService;
import com.deepexi.channel.domain.eo.CcBank;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.util.List;


@Api(value = "银行管理", description = "获取银行列表")
@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @GetMapping()
    @ApiOperation("查询银行列表")
    public Payload<List<BankVO>> listChainPage(){
        List<BankDTO> bankDTOS = bankService.listBank();
        return new Payload<>(ObjectCloneUtils.convertList(bankDTOS, BankVO.class, CloneDirection.OPPOSITE));
    }

//    @GetMapping
//    //@ApiOperation(value = "分页查询", notes = "分页请求")
//    public  Payload findPage(CcBank eo,
//                             @RequestParam(value = "page", defaultValue = "0") Integer page,
//                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
//        return new Payload(bankService.findPage(eo, page, size));
//    }
//
//    @GetMapping("/list")
//    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
//    public Payload findAll(CcBank eo) {
//        return new Payload(bankService.findAll(eo));
//    }
//
//    @GetMapping("/{id}")
//    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
//        return new Payload(bankService.detail(pk));
//    }
//
//
//    @PutMapping("/{id}")
//    @Transactional
////@ApiOperation(value = "根据id修改", notes = "根据id修改CcBank")
//    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcBank eo) {
//     eo.setId(pk);
//     return new Payload(bankService.update(pk, eo));
//    }
//
//    @PostMapping
//    //@ApiOperation(value = "创建CcBank", notes = "创建CcBank")
//    public Payload create(@RequestBody BankVO bankVO) {
//        return new Payload(bankService.create(bankVO.clone(BankDTO.class)));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
////@ApiOperation(value = "根据id删除CcBank", notes = "根据id删除CcBank")
//    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
//        return new Payload(bankService.delete(pk));
//    }
//
//    @DeleteMapping
//    @Transactional
//    //@ApiOperation(value = "根据id批量删除CcBank", notes = "根据id批量删除CcBank")
//    public Payload delete(@RequestParam(required = true) Integer [] ids) {
//        return new Payload(bankService.delete(ids));
//    }

}