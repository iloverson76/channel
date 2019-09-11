package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.distributor.DistributorDO;
import com.deepexi.channel.domain.distributor.DistributorQuery;
import com.deepexi.channel.domain.eo.CcDistributor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DistributorMapper extends BaseMapper<DistributorDO> {

    List<DistributorDO> findPage(DistributorQuery query);
}