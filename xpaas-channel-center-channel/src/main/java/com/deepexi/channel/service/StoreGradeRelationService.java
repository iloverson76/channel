package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreGradeRelationDTO;
import com.deepexi.channel.domain.store.StoreGradeRelationQuery;

import java.util.List;

public interface StoreGradeRelationService {

    List<StoreGradeRelationDTO> findAll(StoreGradeRelationQuery queryy);

    Long save(StoreGradeRelationDTO storeGradeRelationDTO);

    StoreGradeRelationDTO getStoreGradeRelationByStoreId(Long pk);
}
