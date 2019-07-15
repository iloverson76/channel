package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.*;

import java.util.List;

/**
 * promotion
 *
 * @author admin
 */
public interface CommentInfoDAO extends IService<CommentInfoDO> {

    /**
     * 通过条件查询评价列表列表
     *
     * @param query 条件
     * @return List<CommentDetailDO>
     */
    List<CommentDetailDO> listByCommentInfoQuery(CommentInfoQuery query);

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