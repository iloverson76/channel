package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreTypeDAO;
import com.deepexi.channel.domain.eo.CcStoreType;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.mapper.StoreTypeMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreTypeDAOImpl extends ServiceImpl<StoreTypeMapper, CcStoreType> implements StoreTypeDAO {
//    @Autowired
//    StoreTypeMapper storeTypeMapper;
//    @Override
//    public Page<StoreTypeDO> listStoreTypePage(StoreTypeQuery query) {
//        return storeTypeMapper.listStoreTypePage(query);
//    }
}
