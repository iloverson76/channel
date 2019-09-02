package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDTO;

public interface StoreBusinessService {

    Long create(StoreDTO dto);

    Boolean update(StoreDTO dto);

    StoreDTO detail(Integer pk);
}
