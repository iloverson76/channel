package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentLabelTemplateHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface CommentLabelTemplateHistoryMapper extends BaseMapper<CommentLabelTemplateHistoryDO> {


    List<CommentLabelTemplateHistoryDO> selectByLabelTemplateId(@Param("tIds") Set<Long> tIds);
}