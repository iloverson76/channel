package com.deepexi.channel.businness;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;

import java.util.List;

public interface StoreAreaBusinessService {
    List<AreaDTO> getStoreAreaByStoreId(Long pk);
    Boolean saveStoreAreaRelation(StoreDetailDTO dto);

    Boolean updateStoreAreaRelation(StoreDetailDTO dto);

    Boolean deleteStoreAreaRelation(List<Long> ids);
}
