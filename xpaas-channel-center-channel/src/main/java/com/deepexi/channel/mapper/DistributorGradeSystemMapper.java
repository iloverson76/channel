package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcDistributorGradeSystem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DistributorGradeSystemMapper extends BaseMapper<CcDistributorGradeSystem> {

    List<CcDistributorGradeSystem> findList(@Param("eo")  CcDistributorGradeSystem eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}