package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.distributor.DistributorGradeRelationDO;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DistributorGradeRelationMapper extends BaseMapper<DistributorGradeRelationDO> {

}