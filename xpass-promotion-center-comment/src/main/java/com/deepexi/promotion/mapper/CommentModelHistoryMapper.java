package com.deepexi.promotion.mapper;

import java.util.List;

import com.deepexi.promotion.domain.CommentModelHistoryQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentModelHistoryDO;

@Mapper
public interface  CommentModelHistoryMapper extends BaseMapper<CommentModelHistoryDO> {

    List<CommentModelHistoryDO> findAll(CommentModelHistoryQuery dto);

    int deleteBatchByModelIds(@Param("ids") List<Long> ids);
}