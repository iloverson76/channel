package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcDistributorGrade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DistributorGradeMapper extends BaseMapper<CcDistributorGrade> {

    List<CcDistributorGrade> findList(@Param("eo")  CcDistributorGrade eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}