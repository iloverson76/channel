package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.eo.CcDistributor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DistributorMapper extends BaseMapper<CcDistributor> {

    List<CcDistributor> findList(@Param("eo")  CcDistributor eo);

    int deleteByIds(@Param("ids") List<Integer > ids);

}