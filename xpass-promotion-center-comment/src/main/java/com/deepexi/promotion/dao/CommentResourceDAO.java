package com.deepexi.promotion.dao;

import com.deepexi.promotion.domain.CommentResourceDO;

import java.util.List;

/**
 * comment_resource
 * @author liaoxiaoxin
 */
public interface CommentResourceDAO extends CommentLabelAndResourceDAO<CommentResourceDO> {

    /**
     * 通过实体查询列表
     *
     * @param commentResourceDO 实体
     * @return List<CommentResourceDO>
     */
    List<CommentResourceDO> listByDO(CommentResourceDO commentResourceDO);

}