package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreTypeRelationDO;

public interface StoreTypeRelationService {
    Long save(StoreTypeRelationDO storeTypeRelationDO);

    StoreTypeRelationDO getStoreTypeRelationByStoreId(Long pk);
}
