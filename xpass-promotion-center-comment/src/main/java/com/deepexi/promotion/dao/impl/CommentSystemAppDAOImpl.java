package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentSystemAppDAO;
import com.deepexi.promotion.domain.CommentSystemAppDO;
import com.deepexi.promotion.domain.CommentSystemAppQuery;
import com.deepexi.promotion.mapper.CommentSystemAppMapper;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CommentSystemAppDAOImpl
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-05-13 13:46
 */
@Repository
public class CommentSystemAppDAOImpl extends ServiceImpl<CommentSystemAppMapper,CommentSystemAppDO> implements CommentSystemAppDAO {

    @Autowired
    private CommentSystemAppMapper commentSystemAppMapper;

    @Override
    public List<CommentSystemAppDO> listAllCommentSystemApp(CommentSystemAppQuery query) {
        return commentSystemAppMapper.findAll(query);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        return commentSystemAppMapper.deleteByIds(ids);
    }

    @Override
    public int updateBatch(List<CommentSystemAppDO> list) {
        return commentSystemAppMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<CommentSystemAppDO> list) {
        return commentSystemAppMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(CommentSystemAppDO record) {
        return commentSystemAppMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CommentSystemAppDO record) {
        return commentSystemAppMapper.insertOrUpdateSelective(record);
    }
}
