package com.deepexi.promotion.service;

import java.util.List;

import com.deepexi.promotion.domain.CommentModelDTO;
import com.deepexi.promotion.domain.CommentModelQuery;

/**
 * comment_model
 */
public interface CommentModelService{

    /**
     * 分页获取列表
     *
     * @param query 查询参数
     * @return 列表
     */
    List<CommentModelDTO> findCommentModelPage(CommentModelQuery query);
    /**
     * 获取详情
     *
     * @return
     */
    CommentModelDTO getCommentModel(Long id);

    /**
     * 更新
     *
     * @param id
     * @param dto
     * @return
     */
    Boolean updateCommentModel(Long id, CommentModelDTO dto);

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    Boolean createCommentModel(CommentModelDTO dto);


    /**
     * 批量删除
     * @param ids id主键数组
     * @return boolean
     */
    Boolean deleteCommentModels(Long... ids);

    /**
     * 查询某名是否存在
     * @param name  名称
     * @return boolean
     */
    Boolean getCountCondition (CommentModelDTO dto);

    /**
     * 根据id是查询评价对象信息
     * @param ids
     * @return
     */
    List<CommentModelDTO> listCommentModelByIds(Long... ids);



}