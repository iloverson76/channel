package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IBankDAO;
import com.deepexi.channel.domain.bank.BankDO;
import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.bank.BankVO;
import com.deepexi.channel.service.IBankService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements IBankService {

    @Autowired
    IBankDAO iBankDAO;

    @Override
    public List<BankDTO> listBank() {
        List<BankDO> bankDOS = iBankDAO.findAll();
        List<BankDTO> bankDTOS = ObjectCloneUtils.convertList(bankDOS, BankDTO.class, CloneDirection.OPPOSITE);
        return bankDTOS;
    }
}
