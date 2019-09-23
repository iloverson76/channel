package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreTypeRelationDTO;
import com.deepexi.channel.domain.StoreTypeRelationQuery;

import java.util.List;

public interface StoreTypeRelationService {
    Long save(StoreTypeRelationDTO storeTypeRelationDTO);

    StoreTypeRelationDTO getStoreTypeRelationByStoreId(Long pk);

    List<StoreTypeRelationDTO> findAll(StoreTypeRelationQuery storeGradeRelationQuery);

    Boolean removeByStoreId(Long id);

    Boolean removeByStoreIds(List<Long> storeIds);
}
