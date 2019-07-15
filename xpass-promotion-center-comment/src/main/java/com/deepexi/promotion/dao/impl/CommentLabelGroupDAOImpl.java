package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentLabelGroupDAO;
import com.deepexi.promotion.domain.CommentLabelDetailDO;
import com.deepexi.promotion.domain.CommentLabelDetailQuery;
import com.deepexi.promotion.domain.CommentLabelGroupDO;
import com.deepexi.promotion.domain.CommentLabelGroupQuery;
import com.deepexi.promotion.mapper.CommentLabelGroupMapper;
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
public class CommentLabelGroupDAOImpl extends ServiceImpl<CommentLabelGroupMapper, CommentLabelGroupDO> implements CommentLabelGroupDAO {


    @Autowired
    private CommentLabelGroupMapper commentLabelGroupMapper;

    @Override
    public List<CommentLabelGroupDO> listPageCommentLabelGroups(CommentLabelGroupQuery query) {
        return  commentLabelGroupMapper.findAll(query);

    }

    @Override
    public List<CommentLabelGroupDO> listCommentLabelGroups(CommentLabelGroupQuery dto) {
        return commentLabelGroupMapper.findAll(dto);
    }

    @Override
    public CommentLabelGroupDO getCommentLabelGroup (Long id) {
        return commentLabelGroupMapper.selectById(id);
    }

    @Override
    public Boolean updateCommentLabelGroup(CommentLabelGroupDO dto) {
        int result = commentLabelGroupMapper.updateById(dto);
        return result > 0;
    }

    @Override
    public Boolean insertCommentLabelGroup (CommentLabelGroupDO dto) {
        int result = commentLabelGroupMapper.insert(dto);
        return result > 0;
    }

    @Override
    public Boolean deleteCommentLabelGroups (Long ...ids) {
        int result = commentLabelGroupMapper.deleteBatchIds(Arrays.asList(ids));
        return result > 0;
    }
    @Override
    public List<CommentLabelDetailDO> findLabelDetail(CommentLabelDetailQuery query){
        return commentLabelGroupMapper.findLabelDetail(query);
    }
    @Override
    public Integer findSameNameList(String name,Long appId,Long id){
        QueryWrapper<CommentLabelGroupDO> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("name",name);
        if(id!=null){
            queryWrapper.notIn("id",id);
        }
       return commentLabelGroupMapper.selectCount(queryWrapper);
    }

}