package com.deepexi.channel.service;


import com.deepexi.channel.domain.bank.BankAccountDO;
import com.deepexi.channel.domain.bank.BankAccountDTO;

import java.util.List;

public interface BankAccountService {
    Boolean saveBatch(List<BankAccountDTO> bankAccountDTOS);
}
