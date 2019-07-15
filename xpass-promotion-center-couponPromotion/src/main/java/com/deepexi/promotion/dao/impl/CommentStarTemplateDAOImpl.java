package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentStarTemplateDAO;
import com.deepexi.promotion.domain.CommentStarTemplateDO;
import com.deepexi.promotion.domain.CommentStarTemplatePageQuery;
import com.deepexi.promotion.mapper.CommentStarTemplateMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@Repository
public class CommentStarTemplateDAOImpl extends ServiceImpl<CommentStarTemplateMapper, CommentStarTemplateDO> implements CommentStarTemplateDAO {

    @Autowired
    private CommentStarTemplateMapper commentStarTemplateMapper;

    @Override
    public List<CommentStarTemplateDO> findPae(CommentStarTemplatePageQuery query) {
        CommentStarTemplateDO starTemplateDO = query.clone(CommentStarTemplateDO.class);
        QueryWrapper<CommentStarTemplateDO> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(starTemplateDO.getName())) {
            queryWrapper.like("name", starTemplateDO.getName());
        }
        if (starTemplateDO.getAppId() != null){
            queryWrapper.eq("app_id",starTemplateDO.getAppId());
        }
        queryWrapper.orderByDesc("id");
        List<CommentStarTemplateDO> list = commentStarTemplateMapper.selectList(queryWrapper);
        return list;
    }
    @Override
    public List<CommentStarTemplateDO> findList(CommentStarTemplatePageQuery query) {

        QueryWrapper<CommentStarTemplateDO> queryWrapper = new QueryWrapper<>();
        if(query.getIds()!=null){
            queryWrapper.in("id",query.getIds());
        }else {
            return null;
        }
        List<CommentStarTemplateDO> list = commentStarTemplateMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public CommentStarTemplateDO selectById(Long id) {
        QueryWrapper<CommentStarTemplateDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return commentStarTemplateMapper.selectOne(queryWrapper);
    }

    @Override
    public CommentStarTemplateDO selectByAppIdAndName(Long appId, String name) {
        QueryWrapper<CommentStarTemplateDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("name",name);
        return commentStarTemplateMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Long> selectBusinessIds(List<Long> ids) {
        return commentStarTemplateMapper.selectBusinessIds(ids);
    }
}
