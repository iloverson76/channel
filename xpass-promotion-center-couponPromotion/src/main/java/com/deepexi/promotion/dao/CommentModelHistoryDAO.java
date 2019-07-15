package com.deepexi.promotion.dao;

import com.deepexi.promotion.domain.CommentModelHistoryDO;
import com.deepexi.promotion.domain.CommentModelHistoryQuery;

import java.util.List;

/**
 * comment_model_history
 */
public interface CommentModelHistoryDAO  {

    /**
     * 分页获取列表
     *
     * @param dto
     * @param page 当前页
     * @param size 分页大小
     * @return Page
     */
    List<CommentModelHistoryDO> findCommentModelHistoryPage(CommentModelHistoryQuery query);

    /**
     * 新增
     *
     * @param dao
     * @return Boolean
     */
    Boolean createCommentModelHistory(CommentModelHistoryDO dao);

    /**
     * 通过评价对象ID 删除关联评价对象历史
     * @param ids id数组
     * @return Boolean
     */
    Boolean deleteBatchByModelIds(Long... ids);
}