package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentBusinessLabelGroupSetDO;
import com.deepexi.promotion.domain.CommentBusinessTemplateSetQuery;
import com.deepexi.promotion.domain.CommentTemplateDeleteGroupVO;

import java.util.List;

/**
 * comment_business_label_group_set
 *
 * @author admin
 */
public interface CommentBusinessLabelGroupSetDAO extends IService<CommentBusinessLabelGroupSetDO> {

    List<CommentBusinessLabelGroupSetDO> findListByConnectIds(CommentBusinessTemplateSetQuery query);

    int deleteByVO(CommentTemplateDeleteGroupVO vo);
    /**
     * 通过业务对象关联主键删除
     *
     * @param businessModelConnectId 业务对象关联主键
     * @return 成功与否
     */
    boolean deleteByBusinessModelConnectId(Long businessModelConnectId);


    /**
     * 查询列表
     *
     * @param businessLabelGroupSetDO 参数
     * @return 结果集
     */
    List<CommentBusinessLabelGroupSetDO> listByDO(CommentBusinessLabelGroupSetDO businessLabelGroupSetDO);

}