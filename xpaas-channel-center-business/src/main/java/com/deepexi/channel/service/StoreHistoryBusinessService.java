package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreHistoryBusinessDTO;
import com.deepexi.channel.domain.StoreHistoryDTO;
import com.deepexi.channel.domain.StoreHistoryDetailDTO;

public interface StoreHistoryBusinessService {
    StoreHistoryDTO storeDetailHistory2storeHistory(StoreHistoryDetailDTO storeHistoryDetailDTO);

    StoreHistoryBusinessDTO detail(Long pk);

    StoreHistoryDetailDTO storeHistory2StoreDetailHistory(StoreHistoryDTO dto);
}
