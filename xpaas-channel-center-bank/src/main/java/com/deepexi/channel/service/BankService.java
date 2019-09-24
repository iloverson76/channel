package com.deepexi.channel.service;

import com.deepexi.channel.domain.BankDTO;
import com.deepexi.channel.domain.BankQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
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

    Boolean update(BankDTO bankDTO);
}