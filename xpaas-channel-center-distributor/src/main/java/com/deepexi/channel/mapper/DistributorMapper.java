package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.DistributorDO;
import com.deepexi.channel.domain.DistributorQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DistributorMapper extends BaseMapper<DistributorDO> {

    List<DistributorDO> findPage(DistributorQuery query);

    List<String> listDistributorCode();

    List<String> listDistributorName();

    List<String> listDistributorNameEn();
}