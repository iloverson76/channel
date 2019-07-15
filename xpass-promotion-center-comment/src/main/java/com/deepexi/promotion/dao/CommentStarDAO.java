package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentStarDO;

import java.util.List;

/**
 * comment_star
 * @author admin
 */
public interface CommentStarDAO extends IService<CommentStarDO> {

    /**
     * 通过实体查询
     *
     * @param commentStarDO 实体内容
     * @return List<CommentStarDO>
     */
    List<CommentStarDO> listByDO(CommentStarDO commentStarDO);

    /**
     * 通过评价明细id查询星评评价
     *
     * @param ids 评价明细集合
     * @return  List<CommentStarDO>
     */
    List<CommentStarDO> listByCommentDetailIdList(List<Long> ids);

    /**
     * 通过评价明细id查询星评评价
     *
     * @param commentDetailId 评价明细集合
     * @return  List<CommentStarDO>
     */
    List<CommentStarDO> listByCommentDetailId(Long commentDetailId);

}