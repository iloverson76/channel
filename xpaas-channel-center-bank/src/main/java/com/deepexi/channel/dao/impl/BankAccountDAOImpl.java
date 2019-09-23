package com.deepexi.channel.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.BankAccountDAO;
import com.deepexi.channel.domain.BankAccountDO;
import com.deepexi.channel.domain.BankAccountQuery;
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

    @Override
    public List<BankAccountDO> findList(BankAccountQuery query) {
        return bankAccountMapper.findList(query);
    }

//    @Autowired
//    BankAccountMapper bankAccountMapper;

}
