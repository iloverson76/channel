package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemDO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemQuery;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import com.deepexi.channel.domain.eo.CcDistributorGradeSystem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DistributorGradeSystemMapper extends BaseMapper<DistributorGradeSystemDO> {

    List<DistributorGradeSystemDO> findPage(DistributorGradeSystemQuery query);
}