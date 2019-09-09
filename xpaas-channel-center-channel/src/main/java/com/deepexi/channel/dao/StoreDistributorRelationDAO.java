package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.store.StoreDistributorRelationDO;
import com.deepexi.channel.domain.store.StoreDistributorRelationQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/7 14:23
 */
public interface StoreDistributorRelationDAO  extends IService<StoreDistributorRelationDO> {
    List<StoreDistributorRelationDO> findList(StoreDistributorRelationQuery query);

    Boolean deleteByStoreId(long storeId);
}