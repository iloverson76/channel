package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentStarTemplateDetailDAO;
import com.deepexi.promotion.domain.CommentStarTemplateDetailDO;
import com.deepexi.promotion.mapper.CommentStarTemplateDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@Repository
public class CommentStarTemplateDetailDAOImpl extends ServiceImpl<CommentStarTemplateDetailMapper, CommentStarTemplateDetailDO> implements CommentStarTemplateDetailDAO {

    @Autowired
    private CommentStarTemplateDetailMapper commentStarTemplateDetailMapper;

    @Override
    public List<CommentStarTemplateDetailDO> selectByStarTemplateIds(Set<Long> starTemplateIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("star_template_id",starTemplateIds);
        return commentStarTemplateDetailMapper.selectList(queryWrapper);
    }
}
