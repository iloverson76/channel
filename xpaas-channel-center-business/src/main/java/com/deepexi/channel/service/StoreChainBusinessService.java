package com.deepexi.channel.service;

import com.deepexi.channel.domain.ChainDetailDTO;
import com.deepexi.channel.domain.StoreDetailDTO;

import java.util.List;

public interface StoreChainBusinessService {

    Boolean saveStoreChainRelation(StoreDetailDTO dto);

    Boolean updateStoreChainRelation(StoreDetailDTO dto);

    List<ChainDetailDTO> getStoreChainByStoreId(Long pk);

    Boolean deleteStoreChainRelation(List<Long> ids);

}
