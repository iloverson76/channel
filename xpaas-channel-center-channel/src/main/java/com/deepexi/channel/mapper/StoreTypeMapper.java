package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.github.pagehelper.Page;

/**
 * <p>
 * 连锁类型表 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
public interface StoreTypeMapper extends BaseMapper<StoreTypeDO> {

    Page<StoreTypeDO> listStoreTypePage(StoreTypeQuery query);
}
