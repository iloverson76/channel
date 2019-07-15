package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentBusinessModelConnectDAO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectDO;
import com.deepexi.promotion.domain.CommentBusinessModelConnectQuery;
import com.deepexi.promotion.mapper.CommentBusinessModelConnectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * @author zhangyanping
 */
@Repository
@Slf4j
public class CommentBusinessModelConnectDAOImpl extends ServiceImpl<CommentBusinessModelConnectMapper, CommentBusinessModelConnectDO> implements CommentBusinessModelConnectDAO {


    @Resource
    private CommentBusinessModelConnectMapper commentBusinessModelConnectMapper;


    @Override
    public List<CommentBusinessModelConnectDO> listCommentBusinessModelConnects(CommentBusinessModelConnectQuery query) {
        return commentBusinessModelConnectMapper.findAll(query);
    }

    @Override
    public Boolean updateCommentBusinessModelConnect(CommentBusinessModelConnectDO dao) {
        int result = commentBusinessModelConnectMapper.updateById(dao);
        return result > 0;
    }

    @Override
    public Boolean insertCommentBusinessModelConnect (CommentBusinessModelConnectDO dao) {
        int result = commentBusinessModelConnectMapper.insert(dao);
        return result > 0;
    }


    @Override
    public Boolean deleteCommentBusinessModelConnects (Long ...ids) {
        int result = commentBusinessModelConnectMapper.deleteBatchIds(Arrays.asList(ids));
        return result > 0;
    }
    @Override
    public Boolean deleteByCondition(Long[] modelId){
        UpdateWrapper<CommentBusinessModelConnectDO> updateWrapper=new UpdateWrapper<>();
        updateWrapper.set("dr",1);
        updateWrapper.in("model_id",modelId);
        int n = commentBusinessModelConnectMapper.delete(updateWrapper);
        return n>0;
    }

    @Override
    public List<CommentBusinessModelConnectDO> selectByCondition(Long[] modelIds) {
        QueryWrapper<CommentBusinessModelConnectDO> updateWrapper=new QueryWrapper<>();
        updateWrapper.eq("dr",0);
        updateWrapper.in("model_id",modelIds);
        return commentBusinessModelConnectMapper.selectList(updateWrapper);
    }

    @Override
    public List<CommentBusinessModelConnectDO> listCommentBusinessModelConnects(
            List<CommentBusinessModelConnectQuery> entityList) {
        QueryWrapper<CommentBusinessModelConnectDO> wrapper = new QueryWrapper<>();
        //使用or嵌套 eg: or(i -> i.eq("business_id", 1).eq("model_id", 1)
        entityList.stream().forEach(entity ->
                wrapper.or(i -> i.eq("business_id", entity.getBusinessId())
                        .eq("model_id", entity.getModelId()))
        );
        return this.list(wrapper);
    }
}