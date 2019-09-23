package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.StoreGradeRelationDO;
import com.deepexi.channel.domain.StoreGradeRelationQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreGradeRelationMapper extends BaseMapper<StoreGradeRelationDO> {

    List<StoreGradeRelationDO> findAll(StoreGradeRelationQuery query);
}