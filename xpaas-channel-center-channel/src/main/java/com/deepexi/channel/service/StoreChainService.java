package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreChainDTO;
import com.deepexi.channel.domain.store.StoreChainQuery;

import java.util.List;

public interface StoreChainService {

    List<StoreChainDTO> findList(StoreChainQuery query);

    Long save(StoreChainDTO storeChainDTO);

    Boolean removeByStoreId(Long id);

//    List<StoreChainDTO> getStoreChainByStoreId(Long storeId);

    Boolean removeByStoreIds(List<Long> ids);

    Boolean saveBatch(List<StoreChainDTO> storeChainDTOS);

//    List<StoreChainDTO> getStoreChainByChainIds(List<Long> ids);
}
