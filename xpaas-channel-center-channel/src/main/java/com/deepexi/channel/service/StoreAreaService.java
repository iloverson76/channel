package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreAreaDTO;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreAreaService {
    Long save(StoreAreaDTO storeAreaDTO);

    Boolean removeByStoreId(Long id);

    List<StoreAreaDTO> getStoreAreaByStoreId(Long storeId);

    Boolean removeByStoreIds(List<Long> ids);

    Boolean saveBatch(List<StoreAreaDTO> list);
}