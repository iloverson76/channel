package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.*;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
public interface CommentStarTemplateService extends IService<CommentStarTemplateDO> {

    Boolean create(CommentStarTemplateDTO dto);

    Boolean update(CommentStarTemplateDTO dto);

    List<CommentStarTemplateDTO> selectPage(CommentStarTemplatePageQuery query);

    List<CommentStarTemplateHistoryDTO> selectHistoryPage(CommentStarTemplateHistoryQuery query);

    /**
     * 保存星级评价明细信息
     *
     * @param dto 星级评价明细
     * @return
     */
    Boolean saveStarDetail(CommentStarTemplateDetailDTO dto);

    /**
     * 修改星级评价明细信息
     *
     * @param dto 星级评价价值
     * @param id  主键id
     * @return
     */
    Boolean updateStarDetail(Long id, CommentStarTemplateDetailDTO dto);

    /**
     * 保存修改历史记录
     *
     * @param id 主键id
     * @return
     */
    Boolean saveStarHistory(Long id);

    /**
     * 删除星级评价明细记录
     * @param starTemplateId 星级评价id
     * @param id 星级评价明细id
     * @return
     */
    Boolean deleteStarDetail(Long starTemplateId, Long id);

    Boolean delete(Set<Long> ids);

    CommentStarTemplateDTO getStarTemplate(Long id);
}
