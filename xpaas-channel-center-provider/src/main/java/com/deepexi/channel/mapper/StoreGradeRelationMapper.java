package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcStoreGradeRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StoreGradeRelationMapper extends BaseMapper<CcStoreGradeRelation> {

    List<CcStoreGradeRelation> findList(@Param("eo")  CcStoreGradeRelation eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}