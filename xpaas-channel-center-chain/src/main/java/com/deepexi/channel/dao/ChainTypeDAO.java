package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.ChainTypeDO;
import com.deepexi.channel.domain.ChainTypeQuery;

import java.util.List;
import java.util.Set;
/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
public interface ChainTypeDAO extends IService<ChainTypeDO> {
    List<ChainTypeDO> findList(ChainTypeQuery query);

    List<ChainTypeDO> findByChainIdNotInAll(List<Long> chainIdList);

    List<ChainTypeDO> listNotLimitedNode(String tenantId, String appId);

    List<ChainTypeDO> listChildNodes(String tenantId, String appId, String s);

}
