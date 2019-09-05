package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.ChainDAO;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.mapper.ChainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChainDAOImpl extends ServiceImpl<ChainMapper, ChainDO> implements ChainDAO {

    @Autowired
    ChainMapper chainMapper;

    @Override
    public List<ChainDO> findList(ChainQuery query) {
        return chainMapper.findList(query);
    }

    @Override
    public Integer getChainCountByTypeIds(List<Long> typeIds) {
        return chainMapper.getChainCountByTypeIds(typeIds);
    }

    @Override
    public List<ChainDO> findParentList(List<Long> ids) {
        return chainMapper.findParentList(ids);
    }

    @Override
    public boolean updateBatch(List<ChainDO> chainDOS) {
        return chainMapper.updateBatch(chainDOS) > 0 ? true : false;
    }

    @Override
    public List<ChainDO> getChainTreeNode() {
        return chainMapper.getChainTreeNode();
    }

    @Override
    public Integer resetTree(ChainDO chainDO) {
        return chainMapper.resetTree(chainDO);
    }
}
