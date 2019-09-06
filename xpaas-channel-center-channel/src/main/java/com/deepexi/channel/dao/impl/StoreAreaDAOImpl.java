package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreAreaDAO;
import com.deepexi.channel.domain.store.StoreAreaDO;
import com.deepexi.channel.mapper.StoreAreaMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreAreaDAOImpl extends ServiceImpl<StoreAreaMapper, StoreAreaDO> implements StoreAreaDAO {
    @Override
    public Boolean removeByStoreId(Long storeId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_id",storeId);
        return this.remove(queryWrapper);
    }

    @Override
    public List<StoreAreaDO> getByStoreId(Long storeId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_id", storeId);
        return this.list(queryWrapper);
    }
}
