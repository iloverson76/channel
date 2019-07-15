package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentBusinessLabelGroupSetDAO;
import com.deepexi.promotion.domain.CommentBusinessLabelGroupSetDO;
import com.deepexi.promotion.domain.CommentBusinessTemplateSetQuery;
import com.deepexi.promotion.domain.CommentTemplateDeleteGroupVO;
import com.deepexi.promotion.mapper.CommentBusinessLabelGroupSetMapper;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * comment_business_label_group_set
 *
 * @author admin
 */
@Repository
public class CommentBusinessLabelGroupSetDAOImpl
        extends ServiceImpl<CommentBusinessLabelGroupSetMapper, CommentBusinessLabelGroupSetDO>
        implements CommentBusinessLabelGroupSetDAO {

    @Resource
    private CommentBusinessLabelGroupSetMapper commentBusinessLabelGroupSetMapper;

    @Override
    public List<CommentBusinessLabelGroupSetDO> findListByConnectIds(CommentBusinessTemplateSetQuery query){
        if(query.getConnectIds()==null||query.getConnectIds().length<=0){
            return new ArrayList<>();
        }
        QueryWrapper<CommentBusinessLabelGroupSetDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("type",query.getType());
        queryWrapper.in("business_model_connect_id",query.getConnectIds());
        return commentBusinessLabelGroupSetMapper.selectList(queryWrapper);
    }

    @Override
    public int deleteByVO(CommentTemplateDeleteGroupVO vo){
       return commentBusinessLabelGroupSetMapper.deleteByVO(vo);
    }
    @Override
    public boolean deleteByBusinessModelConnectId(Long businessModelConnectId) {
        UpdateWrapper<CommentBusinessLabelGroupSetDO> updateWrapper = new UpdateWrapper<>(
                CommentBusinessLabelGroupSetDO.builder().businessModelConnectId(businessModelConnectId).build());
        return this.remove(updateWrapper);
    }

    @Override
    public List<CommentBusinessLabelGroupSetDO> listByDO(CommentBusinessLabelGroupSetDO businessLabelGroupSetDO) {
        return Optional.ofNullable(businessLabelGroupSetDO)
                .map(item -> this.list(new QueryWrapper<>(item)))
                .orElse(ImmutableList.of());
    }

}