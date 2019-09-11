package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;

import java.util.List;

public interface StoreBusinessService {

    Long create(StoreDetailDTO dto);

    Boolean update(StoreDetailDTO dto);

    StoreDetailDTO detail(Long pk);

    Boolean delete(List<Long> ids);
}
