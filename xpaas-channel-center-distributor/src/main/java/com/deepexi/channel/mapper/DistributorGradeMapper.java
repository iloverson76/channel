package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.DistributorGradeDO;
import com.deepexi.channel.domain.DistributorGradeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DistributorGradeMapper extends BaseMapper<DistributorGradeDO> {

    List<DistributorGradeDO> findPage(DistributorGradeQuery query);

    List<DistributorGradeDO> listParentNodesForCreate(@Param("systemId")long systemId,
                                                      @Param("path") String path);

}