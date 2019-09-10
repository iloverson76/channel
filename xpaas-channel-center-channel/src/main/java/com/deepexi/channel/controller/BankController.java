package com.deepexi.channel.controller;

import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountVO;
import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.bank.BankVO;
import com.deepexi.channel.service.BankAccountService;
import com.deepexi.channel.service.BankService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//import io.swagger.annotations.Api;


@Api(value = "银行管理", description = "获取银行列表")
@RestController
@RequestMapping("/api/v1/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping()
    @ApiOperation("查询银行列表")
    public Payload<List<BankVO>> listChainPage(){
        List<BankDTO> bankDTOS = bankService.listBank();
        return new Payload<>(ObjectCloneUtils.convertList(bankDTOS, BankVO.class, CloneDirection.OPPOSITE));
    }

    @PostMapping()
   @ApiOperation(value = "创建银行账号")
   public Payload<Long> create(@RequestBody BankAccountVO vo) {

            Long id=bankAccountService.create(vo.clone(BankAccountDTO.class,CloneDirection.FORWARD));

        return new Payload<>(id);
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