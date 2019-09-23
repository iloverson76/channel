package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.ChainTypeDAO;
import com.deepexi.channel.domain.ChainTypeDO;
import com.deepexi.channel.domain.ChainTypeQuery;
import com.deepexi.channel.mapper.ChainTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
@Repository
public class ChainTypeDAOImpl extends ServiceImpl<ChainTypeMapper, ChainTypeDO> implements ChainTypeDAO {
    @Autowired
    ChainTypeMapper chainTypeMapper;

    @Override
    public List<ChainTypeDO> findList(ChainTypeQuery query) {
        return chainTypeMapper.findList(query);
    }

    @Override
    public List<ChainTypeDO> findByChainIdNotInAll(List<Long> chainIdList) {
        return chainTypeMapper.selectList(new QueryWrapper<ChainTypeDO>().lambda().notIn(ChainTypeDO::getLinkId, chainIdList));
    }

    @Override
    public List<ChainTypeDO> listNotLimitedNode(String tenantId, String appId) {
        return chainTypeMapper.listNotLimitedNode(tenantId,appId);
    }

    @Override
    public List<ChainTypeDO> listChildNodes(String tenantId, String appId, String idPath) {
        return chainTypeMapper.listChildNodes(tenantId,appId,idPath);
    }
}
