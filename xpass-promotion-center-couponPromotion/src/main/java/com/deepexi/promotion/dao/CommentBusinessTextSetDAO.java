package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentBusinessTextSetDO;

import java.util.List;

/**
 * comment_business_text_set
 */
public interface CommentBusinessTextSetDAO extends IService<CommentBusinessTextSetDO> {
    /**
     * 更新
     *
     * @param dao 更新实体
     * @return Boolean
     */
    Boolean updateCommentBusinessTextSet(CommentBusinessTextSetDO dao);

    /**
     * 新增
     *
     * @param dao  实体
     * @return Boolean
     */
    Boolean createCommentBusinessTextSet(CommentBusinessTextSetDO dao);
}