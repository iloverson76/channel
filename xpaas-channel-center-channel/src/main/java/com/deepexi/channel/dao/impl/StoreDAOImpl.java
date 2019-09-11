package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreDAO;
import com.deepexi.channel.domain.store.StoreDO;
import com.deepexi.channel.domain.store.StoreQuery;
import com.deepexi.channel.mapper.StoreMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreDAOImpl extends ServiceImpl<StoreMapper, StoreDO> implements StoreDAO{
    @Autowired
    StoreMapper storeMapper;
    @Override
    public List<StoreDO> findList(StoreQuery query) {
        return storeMapper.findList(query);
    }
}
