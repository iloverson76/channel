package com.deepexi.channel.dao.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.ChainBankDAO;
import com.deepexi.channel.domain.bank.ChainBankDO;
import com.deepexi.channel.domain.eo.CcChainBank;
import com.deepexi.channel.mapper.ChainBankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChainBankDAOImpl extends ServiceImpl<ChainBankMapper, ChainBankDO> implements ChainBankDAO {

    @Autowired
    ChainBankMapper chainBankMapper;

    @Override
    public List<ChainBankDO> getChainBankByChainId(Long id) {
        return chainBankMapper.getChainBankByChainId(id);
    }
}
