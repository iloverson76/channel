package com.deepexi.channel.service;

import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.bank.BankVO;

import java.util.List;

public interface BankService {
    List<BankDTO> listBank();
}
