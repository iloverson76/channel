package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentDetailDO;
import com.deepexi.promotion.domain.CommentInfoQuery;

import java.util.List;


/**
 * comment_detail
 *
 * @author admin
 */
public interface CommentDetailDAO extends IService<CommentDetailDO> {
    /**
     * 批量更新
     *
     * @param detailDOList 评论详情ID
     * @return 是否成功
     */
    boolean updateBatch(List<CommentDetailDO> detailDOList);

    /**
     * 查询明细的回复和追评(一级)
     *
     * @param parentId 父评论ID
     * @return List<CommentDetailDO>
     */
    List<CommentDetailDO> listReplyAndReview(Long parentId);

    /**
     * 根据parentId 查询回复和追评(层级)
     *
     * @param query query.parentId & query.tenantId
     * @return CommentDetailDO
     */
    List<CommentDetailDO> selectByParentId(CommentInfoQuery query);


    /**
     * 根据父评论Id和评论类型查找评论记录
     *
     * @param commentDetailDo 评论明细实体
     * @param n               限制查找条数
     * @return 评论明细记录
     */
    List<CommentDetailDO> listByParentIdAndCommentTypeLimit(CommentDetailDO commentDetailDo, int n);
}