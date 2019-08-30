package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreGradeDAO;
import com.deepexi.channel.dao.StoreTypeDAO;
import com.deepexi.channel.domain.store.StoreGradeDO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.mapper.StoreGradeMapper;
import com.deepexi.channel.mapper.StoreTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreGradeDAOImpl extends ServiceImpl<StoreGradeMapper, StoreGradeDO> implements StoreGradeDAO {
    @Autowired
    StoreGradeMapper storeGradeMapper;

    @Override
    public List<StoreGradeDO> findList(StoreGradeQuery query) {
        return storeGradeMapper.findList(query);
    }
//    @Override
//    public Page<StoreTypeDO> listStoreTypePage(StoreTypeQuery query) {
//        return storeTypeMapper.listStoreTypePage(query);
//    }
}
