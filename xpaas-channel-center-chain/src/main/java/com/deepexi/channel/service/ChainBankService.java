package com.deepexi.channel.service;

import com.deepexi.channel.domain.ChainBankDTO;
import com.deepexi.channel.domain.ChainBankQuery;

import java.util.List;

/**
 * cc_chain_bank
 */
public interface ChainBankService {

    ChainBankDTO detail(Long id);

    boolean update(ChainBankDTO dto);

    boolean delete(List<Long> ids);

    boolean delete(Long id);

    List<ChainBankDTO> findList(ChainBankQuery chainBankQuery);

    boolean saveBatch(List<ChainBankDTO> chainBankDTOS);

//    List<ChainBankDTO> getChainBankByChainId(Long id);

    boolean deleteByChainId(Long id);

}