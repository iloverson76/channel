package com.deepexi.channel.service;

import com.deepexi.channel.domain.bank.BankDTO;
import com.deepexi.channel.domain.bank.BankQuery;

import java.util.List;

/**
 * cc_bank
 */
public interface BankService {

    /**
    * 创建eo
    * @param bankDTO
    * @return
    */
    Boolean create(BankDTO bankDTO);

    List<BankDTO> findList(BankQuery query);

    List<BankDTO> listBank();

    List<BankDTO> getBankByIds(List<Long> bankIds);

    Boolean createBatch(List<BankDTO> bankDTOS);

    Boolean delete(List<Long> ids);

    Boolean delete(Long id);
}