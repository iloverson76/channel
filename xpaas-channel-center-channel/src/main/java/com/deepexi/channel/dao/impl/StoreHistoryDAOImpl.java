package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreHistoryDAO;
import com.deepexi.channel.domain.store.StoreHistoryDO;
import com.deepexi.channel.domain.store.StoreHistoryQuery;
import com.deepexi.channel.mapper.StoreHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreHistoryDAOImpl extends ServiceImpl<StoreHistoryMapper, StoreHistoryDO> implements StoreHistoryDAO {
    @Autowired
    StoreHistoryMapper storeHistoryMapper;

    @Override
    public List<StoreHistoryDO> findList(StoreHistoryQuery query) {
        return storeHistoryMapper.findList(query);
    }
}
