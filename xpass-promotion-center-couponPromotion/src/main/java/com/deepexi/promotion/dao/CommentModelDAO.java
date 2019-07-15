package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentModelDO;
import com.deepexi.promotion.domain.CommentModelQuery;

import java.util.List;

/**
 * comment_model
 */
public interface CommentModelDAO extends IService<CommentModelDO> {

    /**
     * 分页获取列表
     *
     * @param query 查询参数
     * @return 列表
     */
    List<CommentModelDO> findCommentModelPage(CommentModelQuery query);
    /**
     * 获取详情
     *
     * @return CommentModelDO
     */
    CommentModelDO getCommentModel(Long id);

    /**
     * 更新
     *
     * @param id 主键
     * @param dao 实体
     * @return Boolean
     */
    Boolean updateCommentModel(CommentModelDO dao);

    /**
     * 新增
     *
     * @param dao
     * @return Boolean
     */
    Boolean createCommentModel(CommentModelDO dao);

    /**
     * 批量删除
     *
     * @return
     */
    Boolean deleteCommentModels(Long... ids);

    /**
     * 查询重名
     */
    Boolean getCountCondition(CommentModelDO commentModelDO);

    /**
     * 根据ids查询评价对象
     * @param ids ids
     * @return
     */
    List<CommentModelDO> listCommentModelByIds(Long... ids);

}