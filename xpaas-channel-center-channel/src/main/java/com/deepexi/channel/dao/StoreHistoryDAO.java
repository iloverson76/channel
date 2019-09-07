package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.store.StoreHistoryDO;
import com.deepexi.channel.domain.store.StoreHistoryQuery;

import java.util.List;

public interface StoreHistoryDAO extends IService<StoreHistoryDO> {
    List<StoreHistoryDO> findList(StoreHistoryQuery query);
//    Page<StoreTypeDO> listStoreTypePage(StoreTypeQuery query);
}
