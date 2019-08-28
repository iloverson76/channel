package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.github.pagehelper.Page;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ChainTypeDAO extends IService<ChainTypeDO> {
    List<ChainTypeDO> listChainTypePage(ChainTypeQuery query);

    List<ChainTypeDO> selectListByIds(Collection<Long> idList);

    boolean haveChildren(List<Long> ids);
}
