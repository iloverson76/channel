package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreGradeRelationDTO;
import com.deepexi.channel.domain.store.StoreGradeRelationQuery;

import java.util.List;

public interface StoreGradeRelationService {

    List<StoreGradeRelationDTO> findAll(StoreGradeRelationQuery queryy);

    Long save(StoreGradeRelationDTO storeGradeRelationDTO);

    StoreGradeRelationDTO detail(Long id);

    boolean delete(Long id);

    boolean delete(List<Long> ids);

    StoreGradeRelationDTO getStoreGradeRelationByStoreId(Long pk);

    Boolean removeByStoreId(Long id);

    Boolean removeByStoreIds(List<Long> storeIds);
}
