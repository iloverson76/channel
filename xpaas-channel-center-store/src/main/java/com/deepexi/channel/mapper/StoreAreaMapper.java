package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.StoreAreaDO;
import com.deepexi.channel.domain.StoreAreaQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreAreaMapper extends BaseMapper<StoreAreaDO> {

    List<StoreAreaDO> findList(StoreAreaQuery query);
}