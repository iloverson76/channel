package com.deepexi.promotion.service;

import java.util.List;

import com.deepexi.promotion.domain.CommentModelHistoryDTO;
import com.deepexi.promotion.domain.CommentModelHistoryQuery;

/**
 * comment_model_history
 */
public interface CommentModelHistoryService {

    /**
     * 分页获取列表
     *
     * @param dto 查询参数
     * @return
     */
    List<CommentModelHistoryDTO> findCommentModelHistoryPage(CommentModelHistoryQuery dto);

    /**
     * 新增
     *
     * @param dto 入参
     * @return
     */
    Boolean createCommentModelHistory(CommentModelHistoryDTO dto);

    /**
     * 通过评价对象ID 删除关联评价对象历史
     * @param ids 对象IDS
     * @return
     */
    Boolean deleteBatchByModelIds(Long... ids);
}