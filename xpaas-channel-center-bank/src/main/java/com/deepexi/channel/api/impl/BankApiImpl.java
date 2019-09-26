package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.BankApi;
import com.deepexi.channel.domain.BankDTO;
import com.deepexi.channel.domain.BankQuery;
import com.deepexi.channel.service.BankService;
import com.deepexi.util.pageHelper.PageBean;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 14:45
 */
@Service
public class BankApiImpl implements BankApi {

    @Autowired
    BankService bankService;

    @Override
    public List<BankDTO> listBank(BankQuery query) {
        return bankService.findList(query);
    }

    @Override
    public PageBean<BankDTO> listBankPage(BankQuery query) {
        List<BankDTO> list = bankService.findList(query);
        return new PageBean<>(list);
    }

    @Override
    public List<BankDTO> listBank() {
        return bankService.listBank();
    }

    @Override
    public BankDTO detail(@PathVariable(value = "id") Long id) {
        return bankService.detail(id);
    }

    @Override
    public Boolean create(@RequestBody BankDTO bankDTO) {
        return bankService.create(bankDTO);
    }

    @Override
    public Boolean createBatch(@RequestBody List<BankDTO> bankDTOS) {
        return bankService.createBatch(bankDTOS);
    }

    @Override
    public Boolean delete(@RequestBody List<Long> ids) {
        return bankService.delete(ids);
    }

    @Override
    public Boolean update(@PathVariable(value = "id") Long id, @RequestBody BankDTO bankDTO) {
        bankDTO.setId(id);
        return bankService.update(bankDTO);
    }

    @Override
    public Boolean updateBatch(@RequestBody List<BankDTO> bankDTOS) {
        return bankService.updateBatch(bankDTOS);
    }
}