package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.*;

import java.util.List;

public interface CommentBusinessSetService {

    /**
     * 保存业务对象回评模板
     *
     * @param businessId 业务id
     * @param modelId 对象id
     * @param dto 回评模板实体
     * @return
     */
    Boolean saveBusinessReplyTemplate(Long businessId, Long modelId, CommentBusinessReplyInputDTO dto);

    /**
     * 根据业务id和对象id删除星评设置
     * @param businessId 业务id
     * @param modelId 对象id
     * @return
     */
    Boolean deleteCommentBusinessStarSetByModel(Long businessId, Long modelId);

    /**
     * 查询模板
     */
    List<CommentTemplateDTO> queryTemplateList (CommentBusinessTemplateSetQuery query);


}
