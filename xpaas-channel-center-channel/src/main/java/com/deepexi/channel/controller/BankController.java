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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @PutMapping("/{id}")
    @ApiOperation(value = "根据id修改银行账号")
    public Payload<Boolean> update(@PathVariable(value = "id", required = true) Long  pk, @RequestBody BankAccountVO vo) {

     vo.setId(pk);

     return new Payload<>(bankAccountService.update(vo.clone(BankAccountDTO.class,CloneDirection.FORWARD)));
    }

    @DeleteMapping("/{ids}")
    @ApiOperation(value = "根据id批量删除银行账号")
    public Payload<Boolean> delete(@PathVariable(value = "ids") String ids) {

        return new Payload(bankAccountService.deleteBankAccounts(Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList())));
    }



}