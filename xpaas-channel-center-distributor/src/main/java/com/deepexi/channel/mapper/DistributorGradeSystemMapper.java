package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.DistributorGradeSystemDO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistributorGradeSystemMapper extends BaseMapper<DistributorGradeSystemDO> {

    List<DistributorGradeSystemDO> findPage(DistributorGradeSystemQuery query);

    List<String> listGradeSystemCode();

    List<String> listGradeSystemName();

    List<String> listGradeSystemNameEn();
}