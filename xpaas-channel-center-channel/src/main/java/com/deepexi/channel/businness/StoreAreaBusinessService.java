package com.deepexi.channel.businness;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;

public interface StoreAreaBusinessService {
    AreaDTO getStoreAreaByStoreId(Long pk);
    Long saveStoreAreaRelation(StoreDetailDTO dto);

    Long updateStoreAreaRelation(StoreDetailDTO dto);
}
