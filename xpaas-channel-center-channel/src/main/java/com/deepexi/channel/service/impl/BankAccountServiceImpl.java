package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.BankAccountDAO;
import com.deepexi.channel.dao.*;
import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.service.BankAccountService;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    BankAccountDAO bankAccountDAO;

    @Override
    public Boolean saveBatch(List<BankAccountDTO> bankAccountDTOS) {
        List<BankAccountDO> bankAccountDOS =ObjectCloneUtils.convertList(bankAccountDTOS, BankAccountDO.class);
        return bankAccountDAO.saveBatch(bankAccountDOS);
    }
}
