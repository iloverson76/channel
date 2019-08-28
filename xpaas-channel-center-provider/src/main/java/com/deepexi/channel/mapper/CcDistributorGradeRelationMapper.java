package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CcDistributorGradeRelationMapper extends BaseMapper<CcDistributorGradeRelation> {

    List<CcDistributorGradeRelation> findList(@Param("eo")  CcDistributorGradeRelation eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}