package com.deepexi.promotion.mapper;

import java.util.List;

import com.deepexi.promotion.domain.CommentModelQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentModelDO;

@Mapper
public interface  CommentModelMapper extends BaseMapper<CommentModelDO> {

    List<CommentModelDO> findAll(CommentModelQuery dto);
}