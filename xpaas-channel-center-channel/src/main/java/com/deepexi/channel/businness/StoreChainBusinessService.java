package com.deepexi.channel.businness;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.store.StoreChainDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;

public interface StoreChainBusinessService {

    Long saveStoreChainRelation(StoreDetailDTO dto);

    Long updateStoreChainRelation(StoreDetailDTO dto);

    ChainDTO getStoreChainByStoreId(Long pk);
}
