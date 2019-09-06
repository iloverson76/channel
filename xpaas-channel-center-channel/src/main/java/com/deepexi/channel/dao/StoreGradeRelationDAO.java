package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.store.StoreGradeRelationDO;
import com.deepexi.channel.domain.store.StoreGradeRelationQuery;

import java.util.List;

public interface StoreGradeRelationDAO extends IService<StoreGradeRelationDO>{
    List<StoreGradeRelationDO> findAll(StoreGradeRelationQuery query);
    StoreGradeRelationDO getStoreGradeRelationByStoreId(Long pk);

    Boolean removeByStoreId(Long storeId);
}
