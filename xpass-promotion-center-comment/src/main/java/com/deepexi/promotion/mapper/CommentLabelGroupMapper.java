package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentLabelDetailDO;
import com.deepexi.promotion.domain.CommentLabelDetailQuery;
import com.deepexi.promotion.domain.CommentLabelGroupDO;
import com.deepexi.promotion.domain.CommentLabelGroupQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CommentLabelGroupMapper extends BaseMapper<CommentLabelGroupDO> {


    List<CommentLabelGroupDO> findAll(CommentLabelGroupQuery query);

    int deleteByIds(@Param("ids") List<Long> ids);

    int updateBatch(List<CommentLabelGroupDO> list);

    int batchInsert(@Param("list") List<CommentLabelGroupDO> list);

    int insertOrUpdate(CommentLabelGroupDO record);

    int insertOrUpdateSelective(CommentLabelGroupDO record);

    List<CommentLabelDetailDO> findLabelDetail(CommentLabelDetailQuery query);

}