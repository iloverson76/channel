package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.StoreChainDO;
import com.deepexi.channel.domain.StoreChainQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreChainMapper extends BaseMapper<StoreChainDO> {
    List<StoreChainDO> findList(StoreChainQuery query);
}