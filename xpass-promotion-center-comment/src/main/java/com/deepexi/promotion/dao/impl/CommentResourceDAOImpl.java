package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentResourceDAO;
import com.deepexi.promotion.domain.CommentResourceDO;
import com.deepexi.promotion.mapper.CommentResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Repository
@Slf4j
public class CommentResourceDAOImpl
        extends ServiceImpl<CommentResourceMapper, CommentResourceDO> implements CommentResourceDAO {

    @Resource
    private CommentResourceMapper commentResourceMapper;


    @Override
    public List<CommentResourceDO> listByDO(CommentResourceDO commentResourceDO) {
        return this.list(new QueryWrapper<>(commentResourceDO));
    }
}