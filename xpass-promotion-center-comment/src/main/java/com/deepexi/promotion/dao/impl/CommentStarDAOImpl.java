package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentStarDAO;
import com.deepexi.promotion.domain.CommentStarDO;
import com.deepexi.promotion.mapper.CommentStarMapper;
import com.deepexi.util.CollectionUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author admin
 */
@Repository
@Slf4j
public class CommentStarDAOImpl extends ServiceImpl<CommentStarMapper, CommentStarDO> implements CommentStarDAO {

    @Resource
    private CommentStarMapper commentStarMapper;

    @Override
    public List<CommentStarDO> listByDO(CommentStarDO commentStarDO) {
        return this.list(new QueryWrapper<>(commentStarDO));
    }

    @Override
    public List<CommentStarDO> listByCommentDetailIdList(List<Long> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return ImmutableList.of();
        }
        QueryWrapper<CommentStarDO> wrapper = new QueryWrapper<>();
        wrapper.in("comment_detail_id", ids);
        return this.list(wrapper);
    }

    @Override
    public List<CommentStarDO> listByCommentDetailId(Long commentDetailId) {
        return Optional.ofNullable(commentDetailId)
                .map(item -> this.list(new QueryWrapper<>(CommentStarDO.builder().commentDetailId(item).build())))
                .orElse(ImmutableList.of());
    }
}