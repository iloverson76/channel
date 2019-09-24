package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreDetailDTO;
import com.deepexi.channel.domain.StoreTypeDTO;

import java.util.List;

public interface StoreTypeBusinessService {
    Boolean deleteStoreType(List<Long> ids);

    Long saveStoreTypeRelation(StoreDetailDTO dto);

    StoreTypeDTO getStoreTypeByStoreId(Long pk);

    boolean haveStoreRelation(List<Long> ids);

    Long updateStoreTypeRelation(StoreDetailDTO dto);

    Boolean deleteStoreTypeRelation(List<Long> ids);
}
