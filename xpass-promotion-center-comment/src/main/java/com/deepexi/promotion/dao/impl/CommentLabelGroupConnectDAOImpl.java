package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentLabelGroupConnectDAO;
import com.deepexi.promotion.domain.CommentLabelGroupConnectDO;
import com.deepexi.promotion.domain.CommentLabelGroupConnectQuery;
import com.deepexi.promotion.mapper.CommentLabelGroupConnectMapper;
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
public class CommentLabelGroupConnectDAOImpl extends ServiceImpl<CommentLabelGroupConnectMapper, CommentLabelGroupConnectDO> implements CommentLabelGroupConnectDAO {


    @Autowired
    private CommentLabelGroupConnectMapper commentLabelGroupConnectMapper;

    @Override
    public List<CommentLabelGroupConnectDO> listPageCommentLabelGroupConnects(CommentLabelGroupConnectQuery query) {
        return commentLabelGroupConnectMapper.findAll(query);
    }

    @Override
    public List<CommentLabelGroupConnectDO> listCommentLabelGroupConnects(CommentLabelGroupConnectQuery dto) {
        return commentLabelGroupConnectMapper.findAll(dto);
    }

    @Override
    public CommentLabelGroupConnectDO getCommentLabelGroupConnect(Long id) {
        return commentLabelGroupConnectMapper.selectById(id);
    }

    @Override
    public Boolean updateCommentLabelGroupConnect(CommentLabelGroupConnectDO dto) {
        int result = commentLabelGroupConnectMapper.updateById(dto);
        return result > 0;
    }

    @Override
    public Boolean insertCommentLabelGroupConnect(CommentLabelGroupConnectDO dto) {
        int result = commentLabelGroupConnectMapper.insert(dto);
        return result > 0;
    }


    @Override
    public Boolean deleteCommentLabelGroupConnects(Long... ids) {
        int result = commentLabelGroupConnectMapper.deleteBatchIds(Arrays.asList(ids));
        return result > 0;
    }

    @Override
    public int insertCommentLabelGroupConnectBatch(List<CommentLabelGroupConnectDO> dos) {
        return commentLabelGroupConnectMapper.batchInsert(dos);
    }

    @Override
    public List<Long> selectBusinessIds(Long... id) {
        return commentLabelGroupConnectMapper.selectBusinessIds(Arrays.asList(id));
    }

    @Override
    public List<CommentLabelGroupConnectDO> listCommentLabelGroupConnectsByLabelTemplateIds(Long... labelTemplateIds) {
        QueryWrapper<CommentLabelGroupConnectDO> wrapper = new QueryWrapper<CommentLabelGroupConnectDO>();
        wrapper.lambda().in(CommentLabelGroupConnectDO::getLabelTemplateId,labelTemplateIds);
        return commentLabelGroupConnectMapper.selectList(wrapper);
    }
}