package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.CommentLabelTemplateDTO;
import com.deepexi.promotion.domain.CommentLabelTemplateHistoryDTO;
import com.deepexi.promotion.domain.CommentLabelTemplateHistoryQuery;
import com.deepexi.promotion.domain.CommentLabelTemplateQuery;

import java.util.List;

/**
 * comment_label_template
 */
public interface CommentLabelTemplateService {

    /**
     * 分页获取列表
     *
     * @param eo
     * @param page
     * @param size
     * @return
     */
    List<CommentLabelTemplateDTO> findPage(CommentLabelTemplateQuery eo);


    /**
     * 查询历史记录
     * @param query
     * @return
     */
    List<CommentLabelTemplateHistoryDTO> findHistoryPage(CommentLabelTemplateHistoryQuery query);

    /**
     * 获取详情
     *
     * @return
     */
    CommentLabelTemplateDTO detail(Long pk);

    /**
     * 更新eo
     *
     * @param eo
     * @return
     */
    Boolean update(Long id, CommentLabelTemplateDTO eo);

    /**
     * 创建eo
     *
     * @param eo
     * @return
     */
    Boolean create(CommentLabelTemplateDTO eo);

    /**
     * 批量删除
     *
     * @return
     */
    Boolean delete(Long... pk);

}