package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDetailDTO;

import java.util.List;

public interface StoreTypeBusinessService {
    Boolean deleteStoreType(List<Long> ids);

    Long saveStoreTypeRelation(StoreDetailDTO dto);
}
