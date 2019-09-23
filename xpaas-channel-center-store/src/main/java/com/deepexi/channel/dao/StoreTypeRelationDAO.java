package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.StoreTypeRelationDO;
import com.deepexi.channel.domain.StoreTypeRelationQuery;

import java.util.List;

public interface StoreTypeRelationDAO extends IService<StoreTypeRelationDO>{
    StoreTypeRelationDO getStoreTypeRelationByStoreId(Long pk);

    List<StoreTypeRelationDO> findAll(StoreTypeRelationQuery query);

    Boolean removeByStoreId(Long storeId);

    Boolean removeByStoreIds(List<Long> storeIds);
}
