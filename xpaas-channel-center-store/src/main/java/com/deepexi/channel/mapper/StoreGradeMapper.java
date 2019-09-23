package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.StoreGradeDO;
import com.deepexi.channel.domain.StoreGradeQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreGradeMapper extends BaseMapper<StoreGradeDO> {

    List<StoreGradeDO> findList(StoreGradeQuery query);

    int deleteByIds(@Param("ids") List<Integer > ids);

}