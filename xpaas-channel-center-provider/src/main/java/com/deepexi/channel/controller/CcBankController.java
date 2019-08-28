package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.CcBankService;
import com.deepexi.channel.domain.eo.CcBank;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/银行表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccBanks")
public class  CcBankController {

    @Autowired
    private CcBankService ccBankService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcBank eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(ccBankService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcBank eo) {
        return new Payload(ccBankService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccBankService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcBank")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcBank eo) {
     eo.setId(pk);
     return new Payload(ccBankService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcBank", notes = "创建CcBank")
    public Payload create(@RequestBody CcBank eo) {
        return new Payload(ccBankService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcBank", notes = "根据id删除CcBank")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(ccBankService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcBank", notes = "根据id批量删除CcBank")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(ccBankService.delete(ids));
    }

}