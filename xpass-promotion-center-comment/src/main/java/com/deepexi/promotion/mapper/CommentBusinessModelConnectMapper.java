package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentBusinessModelConnectDO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


@Mapper
public interface  CommentBusinessModelConnectMapper extends BaseMapper<CommentBusinessModelConnectDO> {


    List<CommentBusinessModelConnectDO> findAll(CommentBusinessModelConnectQuery query);

}