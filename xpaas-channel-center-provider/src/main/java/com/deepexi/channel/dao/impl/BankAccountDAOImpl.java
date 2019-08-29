package com.deepexi.channel.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.BankAccountDAO;
import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.eo.CcBankAccount;
import com.deepexi.channel.mapper.BankAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountDAOImpl extends ServiceImpl<BankAccountMapper, CcBankAccount> implements BankAccountDAO {

//    @Autowired
//    BankAccountMapper bankAccountMapper;

}
