package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreGradeRelationDO;

public interface StoreGradeRelationService {
    Long save(StoreGradeRelationDO storeGradeRelationDO);

    StoreGradeRelationDO getStoreGradeRelationByStoreId(Long pk);
}
