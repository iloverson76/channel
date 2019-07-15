package com.deepexi.promotion.mapper;

import java.util.List;

import com.deepexi.promotion.domain.CommentSystemAppQuery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentSystemAppDO;

@Mapper
public interface  CommentSystemAppMapper extends BaseMapper<CommentSystemAppDO> {

    Page<CommentSystemAppDO> findByPage(CommentSystemAppQuery query);

    List<CommentSystemAppDO> findAll(CommentSystemAppQuery query);

    int deleteByIds(@Param("ids") List<Long> ids);

    int updateBatch(List<CommentSystemAppDO> list);

    int batchInsert(@Param("list") List<CommentSystemAppDO> list);

    int insertOrUpdate(CommentSystemAppDO record);

    int insertOrUpdateSelective(CommentSystemAppDO record);

}