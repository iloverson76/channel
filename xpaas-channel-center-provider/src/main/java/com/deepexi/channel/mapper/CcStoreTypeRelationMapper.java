package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStoreTypeRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CcStoreTypeRelationMapper extends BaseMapper<CcStoreTypeRelation> {

    List<CcStoreTypeRelation> findList(@Param("eo")  CcStoreTypeRelation eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}