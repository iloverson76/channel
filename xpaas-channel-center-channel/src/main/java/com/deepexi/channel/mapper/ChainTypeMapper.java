package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.chain.ChainTypeDO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChainTypeMapper extends BaseMapper<ChainTypeDO> {

    List<ChainTypeDO> findList(ChainTypeQuery query);

    int deleteByIds(@Param("ids") List<Integer > ids);

    List<ChainTypeDO> findParentList(@Param("ids")List<Long> ids);
}