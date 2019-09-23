package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.StoreDistributorDO;
import com.deepexi.channel.domain.StoreDistributorRelationDO;
import com.deepexi.channel.domain.StoreDistributorRelationQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreDistributorRelationMapper extends BaseMapper<StoreDistributorRelationDO> {

    List<StoreDistributorRelationDO> findList(StoreDistributorRelationQuery query);

    List<StoreDistributorDO> findParentDistributorByStoreId(@Param("storeId")Long storeId);

//    int deleteByIds(@Param("ids") List<Integer> ids);

}