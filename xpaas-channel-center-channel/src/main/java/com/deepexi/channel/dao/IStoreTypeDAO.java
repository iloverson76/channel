package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.github.pagehelper.Page;

public interface IStoreTypeDAO extends IService<StoreTypeDO> {
    Page<StoreTypeDO> listStoreTypePage(StoreTypeQuery query);
}
