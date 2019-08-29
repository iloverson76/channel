//package com.deepexi.channel.controller;
//
//import com.deepexi.util.config.Payload;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import com.deepexi.channel.service.ChainBankService;
//import com.deepexi.channel.domain.eo.CcChainBank;
//import org.springframework.web.bind.annotation.*;
////import io.swagger.annotations.*;
//
//
////@Api(value = "/银行-账户表", description = "$desc")
//@RestController
//@RequestMapping("/api/v1/ccChainBanks")
//public class ChainBankController {
//
//    @Autowired
//    private ChainBankService chainBankService;
//
//
//    @GetMapping
//    //@ApiOperation(value = "分页查询", notes = "分页请求")
//    public  Payload findPage(CcChainBank eo,
//                             @RequestParam(value = "page", defaultValue = "0") Integer page,
//                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
//        return new Payload(chainBankService.findPage(eo, page, size));
//    }
//
//    @GetMapping("/list")
//    //@ApiOperation(value = "树形查询", notes = "查询全部请求")
//    public Payload findAll(CcChainBank eo) {
//        return new Payload(chainBankService.findAll(eo));
//    }
//
//    @GetMapping("/{id}")
//    public Payload detail(@PathVariable(value = "id", required = true) Integer  pk) {
//        return new Payload(chainBankService.detail(pk));
//    }
//
//
//    @PutMapping("/{id}")
//    @Transactional
////@ApiOperation(value = "根据id修改", notes = "根据id修改CcChainBank")
//    public Payload update(@PathVariable(value = "id", required = true) Integer  pk, @RequestBody CcChainBank eo) {
//     eo.setId(pk);
//     return new Payload(chainBankService.update(pk, eo));
//    }
//
//    @PostMapping
//    //@ApiOperation(value = "创建CcChainBank", notes = "创建CcChainBank")
//    public Payload create(@RequestBody CcChainBank eo) {
//        return new Payload(chainBankService.create(eo));
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
////@ApiOperation(value = "根据id删除CcChainBank", notes = "根据id删除CcChainBank")
//    public Payload delete(@PathVariable(value = "id", required = true) Integer  pk) {
//        return new Payload(chainBankService.delete(pk));
//    }
//
//    @DeleteMapping
//    @Transactional
//    //@ApiOperation(value = "根据id批量删除CcChainBank", notes = "根据id批量删除CcChainBank")
//    public Payload delete(@RequestParam(required = true) Integer [] ids) {
//        return new Payload(chainBankService.delete(ids));
//    }
//
//}