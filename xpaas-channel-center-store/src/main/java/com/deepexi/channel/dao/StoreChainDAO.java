package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.StoreChainDO;
import com.deepexi.channel.domain.StoreChainQuery;

import java.util.List;

public interface StoreChainDAO extends IService<StoreChainDO> {

    List<StoreChainDO> findList (StoreChainQuery query);

    Boolean removeByStoreId(Long storeId);

//    List<StoreChainDO> getByStoreId(Long storeId);

    Boolean removeByStoreIds(List<Long> ids);

//    List<StoreChainDO> getByChainIds(List<Long> ids);
}
