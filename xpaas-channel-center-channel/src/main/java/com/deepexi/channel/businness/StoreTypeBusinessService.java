package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreTypeDTO;

import java.util.List;

public interface StoreTypeBusinessService {
    Boolean deleteStoreType(List<Long> ids);

    Long saveStoreTypeRelation(StoreDetailDTO dto);

    StoreTypeDTO getStoreTypeByStoreId(Long pk);

    boolean haveStoreRelation(List<Long> ids);
}
