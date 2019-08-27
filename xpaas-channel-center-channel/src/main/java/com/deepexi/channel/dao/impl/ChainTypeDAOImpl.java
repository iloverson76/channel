package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.IChainTypeDAO;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.mapper.ChainTypeMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChainTypeDAOImpl extends ServiceImpl<ChainTypeMapper, ChainTypeDO> implements IChainTypeDAO {

    @Autowired
    ChainTypeMapper chainTypeMapper;

    @Override
    public List<ChainTypeDO> listChainTypePage(ChainTypeQuery query) {
        if (query.isPage()) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("app_id", query.getAppId());
        if(StringUtils.isNotEmpty(query.getChainTypeCode())){
            queryWrapper.like("chain_type_code",query.getChainTypeCode());
        }
        if(StringUtils.isNotEmpty(query.getChainTypeCode())){
            queryWrapper.like("chain_type_name",query.getChainTypeName());
        }

        queryWrapper.orderByDesc("created_time");

//        return chainTypeMapper.listChainTypePage(query);
        return baseMapper.selectList(queryWrapper);
    }
}
