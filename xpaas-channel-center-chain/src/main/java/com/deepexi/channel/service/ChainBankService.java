package com.deepexi.channel.service;

import com.deepexi.channel.domain.ChainBankDTO;
import com.deepexi.channel.domain.ChainBankQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
public interface ChainBankService {

    ChainBankDTO detail(Long id);

    boolean update(ChainBankDTO dto);

    boolean updateBatch(List<ChainBankDTO> dtos);

    boolean delete(List<Long> ids);

    boolean delete(Long id);

    List<ChainBankDTO> findList(ChainBankQuery chainBankQuery);

    boolean save(ChainBankDTO dto);

    boolean saveBatch(List<ChainBankDTO> chainBankDTOS);

    boolean deleteByChainId(Long id);

}