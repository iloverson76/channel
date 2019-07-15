package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.*;

import java.util.List;

/**
 * @author zhangyanping
 * @date 2019/5/27
 **/
public interface CommentStarTemplateAndLabelService {

    /**
     * 根据ID查询列表
     * @param query 参数
     * @return list
     */
    List<CommentStarTemplateDTO> findList(CommentStarTemplatePageQuery query);


}
