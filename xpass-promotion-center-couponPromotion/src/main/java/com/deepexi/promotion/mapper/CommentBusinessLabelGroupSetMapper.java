package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentBusinessLabelGroupSetDO;
import com.deepexi.promotion.domain.CommentTemplateDeleteGroupVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentBusinessLabelGroupSetMapper extends BaseMapper<CommentBusinessLabelGroupSetDO> {
     int deleteByVO(CommentTemplateDeleteGroupVO vo);

}