package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentLabelGroupConnectDO;
import com.deepexi.promotion.domain.CommentLabelGroupConnectQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CommentLabelGroupConnectMapper extends BaseMapper<CommentLabelGroupConnectDO> {


    List<CommentLabelGroupConnectDO> findAll(CommentLabelGroupConnectQuery query);

    int deleteByIds(@Param("ids") List<Long> ids);

    int updateBatch(List<CommentLabelGroupConnectDO> list);

    int batchInsert(@Param("list") List<CommentLabelGroupConnectDO> list);

    int insertOrUpdate(CommentLabelGroupConnectDO record);

    int insertOrUpdateSelective(CommentLabelGroupConnectDO record);


    List<Long> selectBusinessIds(@Param("ids") List<Long> ids);
}