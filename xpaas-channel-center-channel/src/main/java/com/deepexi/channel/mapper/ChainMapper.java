package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.chain.ChainDO;
import com.deepexi.channel.domain.chain.ChainQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChainMapper extends BaseMapper<ChainDO> {

    List<ChainDO> findList(ChainQuery chainQuery);

    int deleteByIds(@Param("ids") List<Integer> ids);

    Integer getChainCountByTypeIds(@Param("typeIds")List<Long> typeIds);

    List<ChainDO> findParentList(@Param("ids") List<Long> ids);
}