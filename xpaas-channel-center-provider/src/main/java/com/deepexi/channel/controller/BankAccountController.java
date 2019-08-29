package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.BankAccountService;
import com.deepexi.channel.domain.eo.CcBankAccount;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/银行账户表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccBankAccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcBankAccount eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(bankAccountService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcBankAccount eo) {
        return new Payload(bankAccountService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(bankAccountService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcBankAccount")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcBankAccount eo) {
     eo.setId(pk);
     return new Payload(bankAccountService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcBankAccount", notes = "创建CcBankAccount")
    public Payload create(@RequestBody CcBankAccount eo) {
        return new Payload(bankAccountService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcBankAccount", notes = "根据id删除CcBankAccount")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(bankAccountService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcBankAccount", notes = "根据id批量删除CcBankAccount")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(bankAccountService.delete(ids));
    }

}