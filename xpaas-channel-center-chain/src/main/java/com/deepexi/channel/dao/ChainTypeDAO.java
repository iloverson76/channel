package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.ChainTypeDO;
import com.deepexi.channel.domain.ChainTypeQuery;

import java.util.List;
import java.util.Set;

public interface ChainTypeDAO extends IService<ChainTypeDO> {
    List<ChainTypeDO> findList(ChainTypeQuery query);

    List<ChainTypeDO> findByChainIdNotInAll(List<Long> chainIdList);

    List<ChainTypeDO> listNotLimitedNode(String tenantId, String appId);

    List<ChainTypeDO> listChildNodes(String tenantId, String appId, String s);

}
