package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;

public interface StoreBusinessService {

    Long create(StoreDetailDTO dto);

    Boolean update(StoreDetailDTO dto);

    StoreDTO detail(Integer pk);
}
