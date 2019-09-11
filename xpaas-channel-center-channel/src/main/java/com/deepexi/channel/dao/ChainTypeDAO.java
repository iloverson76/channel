package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;

import java.util.List;
import java.util.Set;

public interface ChainTypeDAO extends IService<ChainTypeDO> {
    List<ChainTypeDO> findList(ChainTypeQuery query);

    List<ChainTypeDO> findByChainIdNotInAll(List<Long> chainIdList);

    List<ChainTypeDO> listNotLimitedNode(String tenantId, String appId);

//    List<ChainTypeDO> selectListByIds(Set<Long> idList);

//    List<ChainTypeDO> findParentList(List<Long> ids);

//    List<ChainTypeDO> listChainTypePage(ChainTypeQuery query);
//
//    List<ChainTypeDO> selectListByIds(Collection<Long> idList);
//
//    boolean haveChildren(List<Long> ids);
}
