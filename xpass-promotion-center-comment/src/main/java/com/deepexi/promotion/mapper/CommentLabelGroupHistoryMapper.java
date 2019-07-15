package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryDO;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface  CommentLabelGroupHistoryMapper extends BaseMapper<CommentLabelGroupHistoryDO> {


    List<CommentLabelGroupHistoryDO> findAll(CommentLabelGroupHistoryQuery query);

    int deleteByIds(@Param("ids") List<Long> ids);

    int updateBatch(List<CommentLabelGroupHistoryDO> list);

    int batchInsert(@Param("list") List<CommentLabelGroupHistoryDO> list);

    int insertOrUpdate(CommentLabelGroupHistoryDO record);

    int insertOrUpdateSelective(CommentLabelGroupHistoryDO record);

    int deleteBatchByGroupsIds(@Param("ids") List<Long> ids);
}