package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreTypeMapper extends BaseMapper<StoreTypeDO> {

    List<StoreTypeDO> findList(StoreTypeQuery storeTypeQuery);

    int deleteByIds(@Param("ids") List<Integer > ids);

}