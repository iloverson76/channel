package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.ChainBankDO;
import com.deepexi.channel.domain.ChainBankQuery;

import java.util.List;

public interface ChainBankDAO extends IService<ChainBankDO> {

    List<ChainBankDO> getChainBankByChainId(Long id);

    boolean deleteByChainId(Long id);

    List<ChainBankDO> findList(ChainBankQuery query);
}
