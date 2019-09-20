package com.deepexi.channel.service;

import com.deepexi.channel.domain.bank.ChainBankDTO;
import com.deepexi.channel.domain.bank.ChainBankQuery;

import java.util.List;

/**
 * cc_chain_bank
 */
public interface ChainBankService {

    List<ChainBankDTO> findList(ChainBankQuery chainBankQuery);

    boolean saveBatch(List<ChainBankDTO> chainBankDTOS);

//    List<ChainBankDTO> getChainBankByChainId(Long id);

    boolean deleteByChainId(Long id);

}