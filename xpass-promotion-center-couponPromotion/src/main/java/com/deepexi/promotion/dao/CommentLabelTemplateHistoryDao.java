package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.CommentLabelTemplateHistoryDO;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
public interface CommentLabelTemplateHistoryDao extends IService<CommentLabelTemplateHistoryDO> {
    int deleteByLabelTemplateIds(Set<Long> labelTemplateIds);

    List<CommentLabelTemplateHistoryDO>  selectByLabelTemplateIds(Set<Long>  tIds);
}
