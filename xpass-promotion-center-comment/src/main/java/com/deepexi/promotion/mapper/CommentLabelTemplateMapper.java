package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentLabelDetailDO;
import com.deepexi.promotion.domain.CommentLabelDetailQuery;
import com.deepexi.promotion.domain.CommentLabelTemplateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentLabelTemplateMapper extends BaseMapper<CommentLabelTemplateDO> {
    List<CommentLabelDetailDO> findLabelDetail(CommentLabelDetailQuery query);

}