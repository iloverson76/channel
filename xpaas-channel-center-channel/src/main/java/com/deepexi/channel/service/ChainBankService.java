package com.deepexi.channel.service;


import com.deepexi.channel.domain.bank.ChainBankDTO;

import java.util.List;

public interface ChainBankService {
    boolean saveBatch(List<ChainBankDTO> chainBankDTOS);
}
