package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentBusinessTextSetDAO;
import com.deepexi.promotion.domain.CommentBusinessTextSetDO;
import com.deepexi.promotion.mapper.CommentBusinessTextSetMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@Repository
@Slf4j
public class CommentBusinessTextSetDAOImpl extends ServiceImpl<CommentBusinessTextSetMapper, CommentBusinessTextSetDO> implements CommentBusinessTextSetDAO {


    @Resource
    private CommentBusinessTextSetMapper commentBusinessTextSetMapper;

    @Override
    public Boolean updateCommentBusinessTextSet(CommentBusinessTextSetDO dao) {
        int result = commentBusinessTextSetMapper.updateById(dao);
        return result > 0;
    }

    @Override
    public Boolean createCommentBusinessTextSet (CommentBusinessTextSetDO dao) {
        int result = commentBusinessTextSetMapper.insert(dao);
        return result > 0;
    }
}