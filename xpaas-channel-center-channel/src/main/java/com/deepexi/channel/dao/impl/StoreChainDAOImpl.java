package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreChainDAO;
import com.deepexi.channel.domain.store.StoreChainDO;
import com.deepexi.channel.mapper.StoreChainMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreChainDAOImpl extends ServiceImpl<StoreChainMapper, StoreChainDO> implements StoreChainDAO {
    @Override
    public Boolean removeByStoreId(Long storeId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_id",storeId);
        return this.remove(queryWrapper);
    }

    @Override
    public List<StoreChainDO> getByStoreId(Long storeId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_id", storeId);
        return this.list(queryWrapper);
    }
}
