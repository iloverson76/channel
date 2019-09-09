package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;

import java.util.List;


public interface ChainDAO extends IService<ChainDO> {

    List<ChainDO> findList(ChainQuery query);

    Integer getChainCountByTypeIds(List<Long> typeIds);

    List<ChainDO> findParentList(List<Long> ids);

    boolean updatePathAndParentIdBatch(List<ChainDO> chainDOS);

    List<ChainDO> getChainTreeNode();

    Integer resetTree(ChainDO chainDO);
}
