package com.deepexi.promotion.service;


import com.deepexi.promotion.domain.*;

import java.util.List;

/**
 * comment_business_star_set
 * @author zhangyanping
 */
public interface CommentBusinessStarSetService {
    /**
     * 添加关联关系
     * @param dto 实体
     * @return 成功或失败
     */
    boolean createCommentBusinessStarSet(CommentBusinessStarSetDTO dto);

    /**
     * 条件删除 connectId和templateId删除
     * @param dto 条件
     * @return 成功或失败
     */
    boolean deleteByCondition(CommentBusinessStarSetDeleteVO vo);
    /**
     * 查询列表
     * @param query 查询参数
     * @return 列表
     */
    List<CommentBusinessStarSetDTO> findListByConnectIds(CommentBusinessTemplateSetQuery query);

    /**
     * 批量保存
     * @param dto 实体
     * @return 影响条数
     */
    boolean saveList (CommentBusinessStarSetInputDTO dto);


}