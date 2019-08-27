package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.IChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.mapper.ChainTypeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChainTypeDAOImpl extends ServiceImpl<ChainTypeMapper, ChainTypeDO> implements IChainTypeDAO {

    @Autowired
    ChainTypeMapper chainTypeMapper;

    @Override
    public Page<ChainTypeDO> listChainTypePage(ChainTypeQuery query) {
        if (query.isPage()) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        return chainTypeMapper.listChainTypePage(query);
    }
}
