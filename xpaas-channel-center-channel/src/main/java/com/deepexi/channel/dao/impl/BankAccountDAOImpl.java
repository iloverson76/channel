package com.deepexi.channel.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.IBankAccountDAO;
import com.deepexi.channel.dao.IBankDAO;
import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.bank.BankDO;
import com.deepexi.channel.mapper.BankAccountMapper;
import com.deepexi.channel.mapper.BankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankAccountDAOImpl extends ServiceImpl<BankAccountMapper, BankAccountDO> implements IBankAccountDAO {

    @Autowired
    BankAccountMapper bankAccountMapper;

}
