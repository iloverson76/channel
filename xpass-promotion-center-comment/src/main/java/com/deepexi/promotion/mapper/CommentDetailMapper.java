package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDetailMapper extends BaseMapper<CommentDetailDO> {


    /**
     * 根据parentId 查询回复和追评
     *
     * @param parentId 父评论ID
     * @return CommentDetailDO
     */
    List<CommentDetailDO> selectByParentId(@Param("parentId") Long parentId);
}