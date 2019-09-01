package com.deepexi.channel.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.BankAccountDAO;
import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.eo.CcBankAccount;
import com.deepexi.channel.mapper.BankAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankAccountDAOImpl extends ServiceImpl<BankAccountMapper, BankAccountDO> implements BankAccountDAO {

    @Autowired
    BankAccountMapper bankAccountMapper;

    @Override
    public List<BankAccountDO> getBankAccountByIds(List<Long> bankAccountIds) {
        return bankAccountMapper.getBankAccountByIds(bankAccountIds);
    }

    @Override
    public int deleteBatchIds(List<Long> ids) {
        return bankAccountMapper.deleteBatchIds(ids);
    }

//    @Autowired
//    BankAccountMapper bankAccountMapper;

}
