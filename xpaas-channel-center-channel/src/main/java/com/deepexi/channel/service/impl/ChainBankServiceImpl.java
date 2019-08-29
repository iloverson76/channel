package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.ChainBankDAO;
import com.deepexi.channel.domain.bank.ChainBankDO;
import com.deepexi.channel.domain.bank.ChainBankDTO;
import com.deepexi.channel.service.ChainBankService;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChainBankServiceImpl implements ChainBankService {
    @Autowired
    ChainBankDAO chainBankDAO;

    @Override
    public boolean saveBatch(List<ChainBankDTO> chainBankDTOS) {
        List<ChainBankDO> list = ObjectCloneUtils.convertList(chainBankDTOS,ChainBankDO.class);
        return chainBankDAO.saveBatch(list);
    }
}
