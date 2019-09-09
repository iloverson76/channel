package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreDistributorDTO;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/7 13:59
 */
public interface StoreDistributorBusinessService {
    List<StoreDistributorDTO> getStoreDistributorByStoreId(Long storeId);

    Boolean saveStoreDistributors(StoreDetailDTO dto);

    Boolean updateStoreDistributorRelation(StoreDetailDTO dto);
}