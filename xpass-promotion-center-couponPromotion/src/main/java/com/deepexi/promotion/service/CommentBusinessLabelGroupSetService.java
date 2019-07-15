package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.domain.CommentBusinessLabelGroupSetDO;
import com.deepexi.promotion.domain.CommentBusinessLabelGroupSetDTO;
import com.deepexi.promotion.domain.CommentTemplateAddGroupDTO;

import java.util.List;

/**
 * comment_business_label_group_set
 *
 * @author admin
 */
public interface CommentBusinessLabelGroupSetService extends IService<CommentBusinessLabelGroupSetDO> {


    /**
     * 批量创建
     *
     * @param dto 需要创建的集合
     * @return 成功与否
     */
    boolean batchCreate(CommentTemplateAddGroupDTO dto);


    /**
     * 重新设置模板与标签组的关联关系
     *
     * @param dto 参数
     * @return 成功与否
     */
    boolean resetByCommentTemplateAddGroupDTO(CommentTemplateAddGroupDTO dto);


    /**
     * 根据business_model_connect_id批量查
     *
     * @param query 条件
     * @return 列表
     */
    List<CommentBusinessLabelGroupSetDTO> findListByConnectIds(CommentBusinessTemplateSetQuery query);

    /**
     * 条件删除
     *
     * @param vo 删除参数
     * @return boolean
     */
    boolean deleteByVO(CommentTemplateDeleteGroupVO vo);

}