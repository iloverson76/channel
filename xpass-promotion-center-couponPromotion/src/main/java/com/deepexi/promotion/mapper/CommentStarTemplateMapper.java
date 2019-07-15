package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentStarTemplateDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@Mapper
public interface CommentStarTemplateMapper extends BaseMapper<CommentStarTemplateDO> {

    List<Long> selectBusinessIds(@Param("ids") List<Long> ids);
}
