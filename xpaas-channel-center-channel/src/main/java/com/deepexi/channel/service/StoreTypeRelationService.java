package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreTypeRelationDTO;

public interface StoreTypeRelationService {
    Long save(StoreTypeRelationDTO storeTypeRelationDTO);

    StoreTypeRelationDTO getStoreTypeRelationByStoreId(Long pk);
}
