package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDetailDTO;

import java.util.List;

public interface StoreGradeBusinessService {
    Boolean deleteGradeType(List<Long> ids);

    Long saveStoreGradeRelation(StoreDetailDTO dto);
}
