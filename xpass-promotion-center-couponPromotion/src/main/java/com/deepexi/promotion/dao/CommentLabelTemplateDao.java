package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentLabelDetailDO;
import com.deepexi.promotion.domain.CommentLabelDetailQuery;
import com.deepexi.promotion.domain.CommentLabelTemplateDO;
import com.deepexi.promotion.domain.CommentLabelTemplateQuery;

import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
public interface CommentLabelTemplateDao  extends IService<CommentLabelTemplateDO> {


    List<CommentLabelTemplateDO> findPage(CommentLabelTemplateQuery query);

    CommentLabelTemplateDO selectById(Long id);

    CommentLabelTemplateDO selectByAppIdAndName(String appId, String name);
    /**
     * 查询标签和关联列表
     * @param query
     * @return
     */
    List<CommentLabelDetailDO> findLabelDetail(CommentLabelDetailQuery query);

    /**
     * 根据标签id 查询当前关联的业务id
     * @param ids 标签id
     * @return 业务id集合
     */
    List<Long> selectBusinessIds(Long... ids);

}
