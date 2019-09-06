package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.StoreGradeRelationDAO;
import com.deepexi.channel.domain.store.StoreGradeRelationDO;
import com.deepexi.channel.domain.store.StoreGradeRelationQuery;
import com.deepexi.channel.mapper.StoreGradeRelationMapper;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreGradeRelationDAOImpl extends ServiceImpl<StoreGradeRelationMapper, StoreGradeRelationDO> implements StoreGradeRelationDAO {
    @Autowired
    StoreGradeRelationMapper storeGradeRelationMapper;

    @Override
    public List<StoreGradeRelationDO> findAll(StoreGradeRelationQuery query) {
        return storeGradeRelationMapper.findAll(query);
    }

    @Override
    public StoreGradeRelationDO getStoreGradeRelationByStoreId(Long storeId) {
        List<StoreGradeRelationDO> storeGradeRelationDOS =  this.list(new QueryWrapper<StoreGradeRelationDO>().lambda()
            .eq(StoreGradeRelationDO::getStoreId,storeId));
        if(CollectionUtil.isEmpty(storeGradeRelationDOS)){
            return null;
        }
        return storeGradeRelationDOS.get(0);
    }

    @Override
    public Boolean removeByStoreId(Long storeId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("store_id",storeId);
        return this.remove(queryWrapper);
    }
}
