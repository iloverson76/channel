package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.store.StoreChainDO;

import java.util.List;

public interface StoreChainDAO extends IService<StoreChainDO> {
    Boolean removeByStoreId(Long storeId);

    List<StoreChainDO> getByStoreId(Long storeId);

    Boolean removeByStoreIds(List<Long> ids);

    List<StoreChainDO> getByChainIds(List<Long> ids);
}
