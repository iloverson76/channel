package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeQuery;

import java.util.List;

public interface StoreTypeDAO extends IService<StoreTypeDO> {
    List<StoreTypeDO> findList(StoreTypeQuery query);
//    Page<StoreTypeDO> listStoreTypePage(StoreTypeQuery query);
}
