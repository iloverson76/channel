package com.deepexi.channel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.channel.domain.store.StoreGradeDO;
import com.deepexi.channel.domain.store.StoreGradeQuery;
import com.deepexi.channel.domain.store.StoreGradeRelationDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StoreGradeRelationMapper extends BaseMapper<StoreGradeRelationDO> {

}