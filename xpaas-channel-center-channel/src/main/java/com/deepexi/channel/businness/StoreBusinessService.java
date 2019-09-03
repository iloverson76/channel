package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;

public interface StoreBusinessService {

    Long create(StoreDetailDTO dto);

    Boolean update(StoreDTO dto);

    StoreDTO detail(Integer pk);
}
