package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentModelHistoryDAO;
import com.deepexi.promotion.domain.CommentModelHistoryDO;
import com.deepexi.promotion.domain.CommentModelHistoryQuery;
import com.deepexi.promotion.mapper.CommentModelHistoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@Repository
@Slf4j
public class CommentModelHistoryDAOImpl extends ServiceImpl<CommentModelHistoryMapper, CommentModelHistoryDO> implements CommentModelHistoryDAO {

    @Resource
    private CommentModelHistoryMapper commentModelHistoryMapper;

    @Override
    public List<CommentModelHistoryDO> findCommentModelHistoryPage(CommentModelHistoryQuery query) {
        return commentModelHistoryMapper.findAll(query);
    }

    @Override
    public Boolean createCommentModelHistory (CommentModelHistoryDO dao) {
        int result = commentModelHistoryMapper.insert(dao);
        return result > 0;
    }
    @Override
    public Boolean deleteBatchByModelIds (Long ...ids) {
        int result = commentModelHistoryMapper.deleteBatchByModelIds(Arrays.asList(ids));
        return result > 0;
    }

}