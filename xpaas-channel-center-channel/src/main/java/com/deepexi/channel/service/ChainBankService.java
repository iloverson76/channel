package com.deepexi.channel.service;

import com.deepexi.channel.domain.bank.ChainBankDTO;

import java.util.List;

/**
 * cc_chain_bank
 */
public interface ChainBankService {
    boolean saveBatch(List<ChainBankDTO> chainBankDTOS);

    List<ChainBankDTO> getChainBankByChainId(Long id);

    boolean deleteByChainId(Long id);

}