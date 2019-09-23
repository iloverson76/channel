package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreDistributorRelationDTO;
import com.deepexi.channel.domain.StoreDistributorRelationQuery;

import java.util.List;

public interface StoreDistributorRelationService {
  
    StoreDistributorRelationDTO detail(Long id);

    Boolean save(StoreDistributorRelationDTO dto);

    Boolean update(StoreDistributorRelationDTO dto);
    
    Boolean delete(Long id);
    
    Boolean delete(List<Long> ids);

    List<StoreDistributorRelationDTO> findList(StoreDistributorRelationQuery query);

    Boolean saveBatch(List<StoreDistributorRelationDTO> relationDTOS);

    Boolean deleteByStoreId(long id);

    Boolean deleteByStoreIds(List<Long> ids);
}
