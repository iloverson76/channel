package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.store.StoreTypeRelationDO;
import com.deepexi.channel.domain.store.StoreTypeRelationQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreTypeRelationMapper extends BaseMapper<StoreTypeRelationDO> {

    List<StoreTypeRelationDO> findAll(StoreTypeRelationQuery query);
}