package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreTypeRelationDAO;
import com.deepexi.channel.domain.store.StoreTypeRelationDO;
import com.deepexi.channel.mapper.StoreTypeRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreTypeRelationDAOImpl extends ServiceImpl<StoreTypeRelationMapper, StoreTypeRelationDO> implements StoreTypeRelationDAO {
    @Autowired
    StoreTypeRelationMapper storeTypeRelationMapper;
}
