package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.store.StoreDistributorRelationDO;
import com.deepexi.channel.domain.store.StoreDistributorRelationQuery;
import com.deepexi.channel.domain.store.StoreGradeDO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreDistributorRelationMapper extends BaseMapper<StoreDistributorRelationDO> {

    List<StoreDistributorRelationDO> findList(StoreDistributorRelationQuery query);

//    int deleteByIds(@Param("ids") List<Integer> ids);

}