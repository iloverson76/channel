package com.deepexi.channel.businness;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainDetailDTO;
import com.deepexi.channel.domain.store.StoreChainDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;

import java.util.List;

public interface StoreChainBusinessService {

    Boolean saveStoreChainRelation(StoreDetailDTO dto);

    Boolean updateStoreChainRelation(StoreDetailDTO dto);

    List<ChainDetailDTO> getStoreChainByStoreId(Long pk);

    Boolean deleteStoreChainRelation(List<Long> ids);
}
