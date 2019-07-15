package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentStarTemplateHistoryDAO;
import com.deepexi.promotion.domain.CommentLabelTemplateDO;
import com.deepexi.promotion.domain.CommentStarTemplateHistoryDO;
import com.deepexi.promotion.mapper.CommentStarTemplateHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@Repository
public class CommentStarTemplateHistoryDAOImpl extends ServiceImpl<CommentStarTemplateHistoryMapper, CommentStarTemplateHistoryDO> implements CommentStarTemplateHistoryDAO {

    @Autowired
    private CommentStarTemplateHistoryMapper commentStarTemplateHistoryMapper;

    @Override
    public int deleteByStarTemplateIds(Set<Long> starTemplateIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("star_template_id",starTemplateIds);
        return commentStarTemplateHistoryMapper.delete(queryWrapper);
    }

    @Override
    public List<CommentStarTemplateHistoryDO> selectByStarTemplateIds(Set<Long> starTemplateIds) {
        QueryWrapper<CommentStarTemplateHistoryDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("star_template_id",starTemplateIds);
        queryWrapper.orderByDesc("id");
        return commentStarTemplateHistoryMapper.selectList(queryWrapper);
    }
}
