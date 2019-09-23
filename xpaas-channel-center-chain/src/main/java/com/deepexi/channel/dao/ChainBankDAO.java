package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.ChainBankDO;
import com.deepexi.channel.domain.ChainBankQuery;

import java.util.List;
/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
public interface ChainBankDAO extends IService<ChainBankDO> {

    List<ChainBankDO> getChainBankByChainId(Long id);

    boolean deleteByChainId(Long id);

    List<ChainBankDO> findList(ChainBankQuery query);
}
