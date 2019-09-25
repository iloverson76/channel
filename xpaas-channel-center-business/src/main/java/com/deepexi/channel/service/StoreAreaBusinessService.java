package com.deepexi.channel.service;

import com.deepexi.channel.domain.AreaBusiDTO;
import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.StoreDetailDTO;

import java.util.List;

public interface StoreAreaBusinessService {
    List<AreaBusiDTO> getStoreAreaByStoreId(Long pk);
    Boolean saveStoreAreaRelation(StoreDetailDTO dto);

    Boolean updateStoreAreaRelation(StoreDetailDTO dto);

    Boolean deleteStoreAreaRelation(List<Long> ids);
}
