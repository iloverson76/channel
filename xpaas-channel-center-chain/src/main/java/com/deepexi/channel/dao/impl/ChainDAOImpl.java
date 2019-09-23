package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.ChainDAO;
import com.deepexi.channel.domain.ChainDO;
import com.deepexi.channel.domain.ChainQuery;
import com.deepexi.channel.mapper.ChainMapper;
import org.h2.command.dml.Update;
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
    public boolean updatePathAndParentIdBatch(List<ChainDO> chainDOS) {
        return chainMapper.updatePathAndParentIdBatch(chainDOS) > 0 ? true : false;
    }

    @Override
    public Boolean updatePathAndParentId(ChainDO chainDO) {
        return chainMapper.updatePathAndParentId(chainDO);
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
