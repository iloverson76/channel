package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentLabelGroupHistoryDAO;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryDO;
import com.deepexi.promotion.domain.CommentLabelGroupHistoryQuery;
import com.deepexi.promotion.mapper.CommentLabelGroupHistoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * @author zhangyanping
 */
@Service
@Slf4j
public class CommentLabelGroupHistoryDAOImpl extends ServiceImpl<CommentLabelGroupHistoryMapper, CommentLabelGroupHistoryDO> implements CommentLabelGroupHistoryDAO {

    @Autowired
    private CommentLabelGroupHistoryMapper commentLabelGroupHistoryMapper;

    @Override
    public List<CommentLabelGroupHistoryDO> listPageCommentLabelGroupHistorys(CommentLabelGroupHistoryQuery query) {
        return commentLabelGroupHistoryMapper.findAll(query);
    }

    @Override
    public List<CommentLabelGroupHistoryDO> listCommentLabelGroupHistorys(CommentLabelGroupHistoryQuery dto) {
        return commentLabelGroupHistoryMapper.findAll(dto);
    }

    @Override
    public CommentLabelGroupHistoryDO getCommentLabelGroupHistory (Long  id) {
       return commentLabelGroupHistoryMapper.selectById(id);
    }

    @Override
    public Boolean updateCommentLabelGroupHistory(CommentLabelGroupHistoryDO dto) {
        int result = commentLabelGroupHistoryMapper.updateById(dto);
        return result > 0;
    }

    @Override
    public Boolean insertCommentLabelGroupHistory (CommentLabelGroupHistoryDO dto) {
        int result = commentLabelGroupHistoryMapper.insert(dto);
        return result > 0;
    }


    @Override
    public Boolean deleteCommentLabelGroupHistorys (Long ...ids) {
        int result = commentLabelGroupHistoryMapper.deleteBatchIds(Arrays.asList(ids));
        return result > 0;
    }
    @Override
    public Boolean deleteBatchByGroupsIds (Long ...ids) {
        int result = commentLabelGroupHistoryMapper.deleteBatchByGroupsIds(Arrays.asList(ids));
        return result > 0;
    }
}