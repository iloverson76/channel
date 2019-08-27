package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.github.pagehelper.Page;

public interface IChainTypeDAO extends IService<ChainTypeDO> {
    Page<ChainTypeDO> listChainTypePage(ChainTypeQuery query);
}
