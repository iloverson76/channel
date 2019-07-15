package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.CommentStarTemplateDetailDTO;

import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/24
 **/
public interface CommentStarTemplateDetailService {
    Boolean create(CommentStarTemplateDetailDTO dto);

    /**
     * 根据星评评价ID列表查询
     * @param starTemplateIds 星评评价ID数组
     * @return
     */
    List<CommentStarTemplateDetailDTO> queryByTemplateIds(List<Long> starTemplateIds);

    /**
     * 根据星评模板明细的ID列表查询
     * @param ids 星评模板明细的ID列表
     * @return CommentStarTemplateDetailDTO
     */
    List<CommentStarTemplateDetailDTO> queryByIds(List<Long> ids);
}
