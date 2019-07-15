package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentLabelDetailDO;
import com.deepexi.promotion.domain.CommentLabelDetailQuery;
import com.deepexi.promotion.domain.CommentStarTemplateDO;
import com.deepexi.promotion.domain.CommentStarTemplatePageQuery;

import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
public interface CommentStarTemplateDAO  extends IService<CommentStarTemplateDO> {
    List<CommentStarTemplateDO> findPae(CommentStarTemplatePageQuery query);

    List<CommentStarTemplateDO> findList(CommentStarTemplatePageQuery query);

    CommentStarTemplateDO selectById(Long id);

    CommentStarTemplateDO selectByAppIdAndName(Long appId,String name);

    List<Long> selectBusinessIds(List<Long> id);
}
