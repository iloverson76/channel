package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.store.StoreAreaDO;

import java.util.List;

public interface StoreAreaDAO extends IService<StoreAreaDO> {
    Boolean removeByStoreId(Long storeId);

    List<StoreAreaDO> getByStoreId(Long storeId);

    Boolean removeByStoreIds(List<Long> storeIds);
}
