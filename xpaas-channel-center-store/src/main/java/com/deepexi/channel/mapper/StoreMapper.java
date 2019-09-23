package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.StoreDO;
import com.deepexi.channel.domain.StoreQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper extends BaseMapper<StoreDO> {

    List<StoreDO> findList(StoreQuery query);

//    int deleteByIds(@Param("ids") List<Integer > ids);

}