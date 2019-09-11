package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.store.StoreHistoryDO;
import com.deepexi.channel.domain.store.StoreHistoryQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreHistoryMapper extends BaseMapper<StoreHistoryDO> {

    List<StoreHistoryDO> findList(StoreHistoryQuery query);

    Integer getStoreHistoryCountByStoreId(@Param("storeId") Long storeId);
}