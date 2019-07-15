package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentAppBusinessDAO;
import com.deepexi.promotion.domain.CommentAppBusinessDO;
import com.deepexi.promotion.domain.CommentAppBusinessQuery;
import com.deepexi.promotion.mapper.CommentAppBusinessMapper;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName CommentAppBusinessDAOImpl
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-05-13 14:31
 */
@Repository
public class CommentAppBusinessDAOImpl extends ServiceImpl<CommentAppBusinessMapper, CommentAppBusinessDO> implements CommentAppBusinessDAO {

    @Autowired
    private CommentAppBusinessMapper commentAppBusinessMapper;

    @Override
    public List<CommentAppBusinessDO> listAll(CommentAppBusinessQuery query) {
        return commentAppBusinessMapper.findAll(query);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
        return commentAppBusinessMapper.deleteByIds(ids);
    }

    @Override
    public int updateBatch(List<CommentAppBusinessDO> list) {
        return commentAppBusinessMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<CommentAppBusinessDO> list) {
        return commentAppBusinessMapper.batchInsert(list);
    }

    @Override
    public int insertOrUpdate(CommentAppBusinessDO record) {
        return commentAppBusinessMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(CommentAppBusinessDO record) {
        return commentAppBusinessMapper.insertOrUpdateSelective(record);
    }
}
