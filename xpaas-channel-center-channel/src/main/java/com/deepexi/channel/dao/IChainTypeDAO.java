package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.github.pagehelper.Page;

import java.util.List;

public interface IChainTypeDAO extends IService<ChainTypeDO> {
    List<ChainTypeDO> listChainTypePage(ChainTypeQuery query);
}
