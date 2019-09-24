package com.deepexi.channel.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.BankDAO;
import com.deepexi.channel.domain.BankDO;
import com.deepexi.channel.domain.BankQuery;
import com.deepexi.channel.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
@Repository
public class BankDAOImpl  extends ServiceImpl<BankMapper, BankDO> implements BankDAO {

    @Autowired
    BankMapper bankMapper;

    @Override
    public List<BankDO> findAll() {
        return bankMapper.findList(null);
    }

    @Override
    public List<BankDO> getBankByIds(List<Long> bankIds) {
        return bankMapper.getBankByIds(bankIds);
    }

    @Override
    public List<BankDO> findList(BankQuery query) {
        return bankMapper.findList(query);
    }

}
