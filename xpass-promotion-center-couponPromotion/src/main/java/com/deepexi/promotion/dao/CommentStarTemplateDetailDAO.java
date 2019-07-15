package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentStarTemplateDetailDO;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
public interface CommentStarTemplateDetailDAO extends IService<CommentStarTemplateDetailDO> {
    List<CommentStarTemplateDetailDO> selectByStarTemplateIds(Set<Long> starTemplateIds);
}
