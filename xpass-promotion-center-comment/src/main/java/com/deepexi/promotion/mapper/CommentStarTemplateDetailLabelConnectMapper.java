package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentStarTemplateDetailLabelConnectDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@Mapper
public interface CommentStarTemplateDetailLabelConnectMapper extends BaseMapper<CommentStarTemplateDetailLabelConnectDO> {
    int deleteByDO(@Param("connects") Set<CommentStarTemplateDetailLabelConnectDO> connects, @Param("updateBy") String updateBy);

    List<Long> selectStarTemplateByLabelIds(@Param("ids") List<Long> ids);
}
