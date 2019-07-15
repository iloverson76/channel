package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentModelDAO;
import com.deepexi.promotion.domain.CommentModelDO;
import com.deepexi.promotion.domain.CommentModelQuery;
import com.deepexi.promotion.mapper.CommentModelMapper;
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
public class CommentModelDAOImpl extends ServiceImpl<CommentModelMapper, CommentModelDO> implements CommentModelDAO {

    @Resource
    private CommentModelMapper commentModelMapper;

    @Override
    public List<CommentModelDO> findCommentModelPage(CommentModelQuery query) {
        return commentModelMapper.findAll(query);
    }
    @Override
    public CommentModelDO getCommentModel (Long  id) {
        return commentModelMapper.selectById(id);
    }
    @Override
    public Boolean updateCommentModel(CommentModelDO dao) {
        int result = commentModelMapper.updateById(dao);
        return result > 0;
    }

    @Override
    public Boolean createCommentModel (CommentModelDO dao) {
        int result1 = commentModelMapper.insert(dao);
        return result1>0;
    }


    @Override
    public Boolean deleteCommentModels (Long ...ids) {
        int result = commentModelMapper.deleteBatchIds(Arrays.asList(ids));
        return result > 0;
    }

    @Override
    public Boolean getCountCondition (CommentModelDO modelDO) {
        QueryWrapper<CommentModelDO> queryWrapper=new QueryWrapper<>();
        if(modelDO.getId()!=null){
            queryWrapper.eq("id",modelDO.getId());
        }
        if(modelDO.getName()!=null){
            queryWrapper.eq("name",modelDO.getName());
        }
        if(modelDO.getIdentificationCode()!=null){
            queryWrapper.eq("identification_code",modelDO.getIdentificationCode());
        }
        int result = commentModelMapper.selectCount(queryWrapper);
        return result > 0;
    }

    @Override
    public List<CommentModelDO> listCommentModelByIds(Long... ids) {
        QueryWrapper<CommentModelDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id",ids);
        return commentModelMapper.selectList(queryWrapper);
    }


}