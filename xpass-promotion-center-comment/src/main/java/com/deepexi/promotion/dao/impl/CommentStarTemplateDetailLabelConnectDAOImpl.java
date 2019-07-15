package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentStarTemplateDetailLabelConnectDAO;
import com.deepexi.promotion.domain.CommentStarTemplateDetailLabelConnectDO;
import com.deepexi.promotion.mapper.CommentStarTemplateDetailLabelConnectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@Repository
public class CommentStarTemplateDetailLabelConnectDAOImpl extends ServiceImpl<CommentStarTemplateDetailLabelConnectMapper, CommentStarTemplateDetailLabelConnectDO> implements CommentStarTemplateDetailLabelConnectDAO {

    @Autowired
    private CommentStarTemplateDetailLabelConnectMapper commentStarTemplateDetailLabelConnectMapper;

    @Override
    public List<CommentStarTemplateDetailLabelConnectDO> selectByStarTemplateDetailIds(Set<Long> detailIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("star_template_detail_id",detailIds);
        return commentStarTemplateDetailLabelConnectMapper.selectList(queryWrapper);
    }

    @Override
    public int removeByStarTemplateDetailIds(Set<Long> detailIds) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("star_template_detail_id",detailIds);
        return commentStarTemplateDetailLabelConnectMapper.delete(queryWrapper);
    }

    @Override
    public int removeByDO(Set<CommentStarTemplateDetailLabelConnectDO> connects,String updateBy) {
        return commentStarTemplateDetailLabelConnectMapper.deleteByDO(connects,updateBy);
    }

    @Override
    public List<CommentStarTemplateDetailLabelConnectDO> selectByLabelTemplateIds(Long... LabelIds) {
        QueryWrapper<CommentStarTemplateDetailLabelConnectDO> queryWrapper = new QueryWrapper<CommentStarTemplateDetailLabelConnectDO>();
        queryWrapper.lambda().in(CommentStarTemplateDetailLabelConnectDO::getLabelTemplateId,LabelIds);
        return commentStarTemplateDetailLabelConnectMapper.selectList(queryWrapper);
    }
}
