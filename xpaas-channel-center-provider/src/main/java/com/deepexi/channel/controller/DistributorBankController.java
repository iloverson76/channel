package com.deepexi.channel.controller;

import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.deepexi.channel.service.DistributorBankService;
import com.deepexi.channel.domain.eo.CcDistributorBank;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.*;


//@Api(value = "/经销商-银行表", description = "$desc")
@RestController
@RequestMapping("/api/v1/ccDistributorBanks")
public class DistributorBankController {

    @Autowired
    private DistributorBankService distributorBankService;


    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CcDistributorBank eo,
                             @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new Payload(distributorBankService.findPage(eo, page, size));
    }

    @GetMapping("/list")
    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
    public Payload findAll(CcDistributorBank eo) {
        return new Payload(distributorBankService.findAll(eo));
    }

    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(distributorBankService.detail(pk));
    }


    @PutMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id修改", notes = "根据id修改CcDistributorBank")
    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcDistributorBank eo) {
     eo.setId(pk);
     return new Payload(distributorBankService.update(pk, eo));
    }

    @PostMapping
    //@ApiOperation(value = "创建CcDistributorBank", notes = "创建CcDistributorBank")
    public Payload create(@RequestBody CcDistributorBank eo) {
        return new Payload(distributorBankService.create(eo));
    }

    @DeleteMapping("/{id}")
    @Transactional
//@ApiOperation(value = "根据id删除CcDistributorBank", notes = "根据id删除CcDistributorBank")
    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
        return new Payload(distributorBankService.delete(pk));
    }

    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除CcDistributorBank", notes = "根据id批量删除CcDistributorBank")
    public Payload delete(@RequestParam(required = true) Integer [] ids) {
        return new Payload(distributorBankService.delete(ids));
    }

}