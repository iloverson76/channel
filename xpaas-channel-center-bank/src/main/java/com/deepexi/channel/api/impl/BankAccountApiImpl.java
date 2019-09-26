package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.BankAccountApi;
import com.deepexi.channel.domain.BankAccountDTO;
import com.deepexi.channel.domain.BankAccountQuery;
import com.deepexi.channel.service.BankAccountService;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 15:22
 */
@Service
public class BankAccountApiImpl implements BankAccountApi {

    @Autowired
    BankAccountService bankAccountService;

    @Override
    public List<BankAccountDTO> findList(BankAccountQuery query) {
        return bankAccountService.findList(query);
    }

    @Override
    public PageBean<BankAccountDTO> listBankAccountPage(BankAccountQuery query) {
        List<BankAccountDTO> list = bankAccountService.findList(query);
        return new PageBean<>(list);
    }

    @Override
    public Boolean delete(@RequestBody List<Long> ids) {
        return bankAccountService.delete(ids);
    }

    @Override
    public Long create(@RequestBody BankAccountDTO dto) {
        return bankAccountService.create(dto);
    }

    @Override
    public List<BankAccountDTO> saveBatch(@RequestBody List<BankAccountDTO> bankAccountDTOS) {
        return bankAccountService.saveBatch(bankAccountDTOS);
    }

    @Override
    public Boolean update(@PathVariable(value = "id") Long id, @RequestBody BankAccountDTO dto){
        dto.setId(id);
        return bankAccountService.update(dto);
    }

    @Override
    public Boolean updateBatch(@RequestBody List<BankAccountDTO> dtos) {
        return bankAccountService.updateBatch(dtos);
    }
}