package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentStarTemplateHistoryDO;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
public interface CommentStarTemplateHistoryDAO extends IService<CommentStarTemplateHistoryDO> {
    int deleteByStarTemplateIds(Set<Long> tIds);

    List<CommentStarTemplateHistoryDO> selectByStarTemplateIds(Set<Long> starTemplateIds);
}
