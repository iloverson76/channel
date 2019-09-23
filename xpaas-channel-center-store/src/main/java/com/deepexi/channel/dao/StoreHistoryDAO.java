package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.StoreHistoryDO;
import com.deepexi.channel.domain.StoreHistoryQuery;

import java.util.List;

public interface StoreHistoryDAO extends IService<StoreHistoryDO> {
    List<StoreHistoryDO> findList(StoreHistoryQuery query);

    Integer getStoreHistoryCountByStoreId(Long storeId);
//    Page<StoreTypeDO> listStoreTypePage(StoreTypeQuery query);
}
