package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.store.StoreDO;
import com.deepexi.channel.domain.store.StoreQuery;

import java.util.List;

public interface StoreDAO extends IService<StoreDO> {
    List<StoreDO> findList(StoreQuery query);
//    Page<StoreTypeDO> listStoreTypePage(StoreTypeQuery query);
}
