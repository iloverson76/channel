package com.deepexi.channel.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.ChainBankDAO;
import com.deepexi.channel.domain.bank.ChainBankDO;
import com.deepexi.channel.mapper.ChainBankMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ChainBankDAOImpl extends ServiceImpl<ChainBankMapper, ChainBankDO> implements ChainBankDAO {

}
