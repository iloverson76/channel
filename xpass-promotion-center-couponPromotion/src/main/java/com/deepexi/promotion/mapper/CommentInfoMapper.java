package com.deepexi.promotion.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author admin
 */
@Mapper
public interface CommentInfoMapper extends BaseMapper<CommentInfoDO> {

    /**
     * 通过条件查询评价列表列表
     *
     * @param query 条件
     * @return List<CommentDetailDO>
     */
    Page<CommentDetailDO> listByCommentInfoQuery(CommentInfoQuery query);

    /**
     * 根据参数统计评论数量
     *
     * @param commentCountQuery CommentCountQuery
     * @return 统计数量
     */
    long countComment(CommentCountQuery commentCountQuery);

    /**
     * 根据统计分类查询评价列表列表
     *
     * @param query 条件
     * @return List<CommentDetailDO>
     */
    List<CommentDetailDO> listCommentByStatisticType(CommentStatisticTypeQuery query);


}