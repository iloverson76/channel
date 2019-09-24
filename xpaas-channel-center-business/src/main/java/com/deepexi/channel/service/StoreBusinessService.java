package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreDetailDTO;

import java.util.List;

public interface StoreBusinessService {

    Long create(StoreDetailDTO dto);

    Boolean update(StoreDetailDTO dto);

    StoreDetailDTO detail(Long pk);

    Boolean delete(List<Long> ids);
}
