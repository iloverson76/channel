package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStoreDistributorRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreDistributorRelationMapper extends BaseMapper<CcStoreDistributorRelation> {

    List<CcStoreDistributorRelation> findList(@Param("eo")  CcStoreDistributorRelation eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}