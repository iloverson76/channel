package com.deepexi.promotion.service;

import java.util.List;

import com.deepexi.promotion.domain.CommentBusinessTemplateSetQuery;
import com.deepexi.promotion.domain.CommentBusinessTextSetDTO;

/**
 * comment_business_text_set
 */
public interface CommentBusinessTextSetService {

    /**
     * 更新
     *
     * @param id
     * @param dto
     * @return
     */
    Boolean updateCommentBusinessTextSet(Long id, CommentBusinessTextSetDTO dto);

    /**
     * 批量查询
     * @param ids connectId集合
     * @return List<CommentBusinessTextSetDTO>
     */
    List<CommentBusinessTextSetDTO> findListByConnectIds (CommentBusinessTemplateSetQuery query);

}