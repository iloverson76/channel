package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreGradeDTO;

import java.util.List;

public interface StoreGradeBusinessService {
    Boolean deleteGradeType(List<Long> ids);

    Long saveStoreGradeRelation(StoreDetailDTO dto);

    StoreGradeDTO getStroeGradeByStoreId(Long pk);
}
