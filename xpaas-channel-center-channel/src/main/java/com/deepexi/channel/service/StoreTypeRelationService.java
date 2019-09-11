package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreTypeRelationDTO;
import com.deepexi.channel.domain.store.StoreTypeRelationQuery;

import java.util.List;

public interface StoreTypeRelationService {
    Long save(StoreTypeRelationDTO storeTypeRelationDTO);

    StoreTypeRelationDTO getStoreTypeRelationByStoreId(Long pk);

    List<StoreTypeRelationDTO> findAll(StoreTypeRelationQuery storeGradeRelationQuery);

    Boolean removeByStoreId(Long id);

    Boolean removeByStoreIds(List<Long> storeIds);
}
