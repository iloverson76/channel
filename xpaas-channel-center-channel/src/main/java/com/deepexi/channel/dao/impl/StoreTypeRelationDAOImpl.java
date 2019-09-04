package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreTypeRelationDAO;
import com.deepexi.channel.domain.store.StoreTypeRelationDO;
import com.deepexi.channel.domain.store.StoreTypeRelationQuery;
import com.deepexi.channel.mapper.StoreTypeRelationMapper;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreTypeRelationDAOImpl extends ServiceImpl<StoreTypeRelationMapper, StoreTypeRelationDO> implements StoreTypeRelationDAO {
    @Autowired
    StoreTypeRelationMapper storeTypeRelationMapper;

    @Override
    public StoreTypeRelationDO getStoreTypeRelationByStoreId(Long storeId) {
        List<StoreTypeRelationDO> storeTypeRelationDOS =  this.list(new QueryWrapper<StoreTypeRelationDO>().lambda()
                .eq(StoreTypeRelationDO::getStoreId,storeId));
        if(CollectionUtil.isEmpty(storeTypeRelationDOS)){
            return null;
        }
        return storeTypeRelationDOS.get(0);
    }

    @Override
    public List<StoreTypeRelationDO> findAll(StoreTypeRelationQuery query) {
        return storeTypeRelationMapper.findAll(query);
    }
}
