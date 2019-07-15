package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentDetailDAO;
import com.deepexi.promotion.domain.CommentDetailDO;
import com.deepexi.promotion.domain.CommentInfoQuery;
import com.deepexi.promotion.mapper.CommentDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Repository
@Slf4j
public class CommentDetailDAOImpl
        extends ServiceImpl<CommentDetailMapper, CommentDetailDO> implements CommentDetailDAO {

    @Resource
    private CommentDetailMapper commentDetailMapper;

    @Override
    public List<CommentDetailDO> listReplyAndReview(Long parentId) {
        QueryWrapper<CommentDetailDO> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        return this.list(wrapper);
    }

    @Override
    public List<CommentDetailDO> selectByParentId(CommentInfoQuery query) {
        return commentDetailMapper.selectByParentId(query.getCommentDetailId());
    }

    @Override
    public boolean updateBatch(List<CommentDetailDO> detailDOList) {
        return this.updateBatchById(detailDOList);
    }

    @Override
    public List<CommentDetailDO> listByParentIdAndCommentTypeLimit(CommentDetailDO commentDetailDo, int n) {
        LambdaQueryWrapper<CommentDetailDO> wrapper = new QueryWrapper<CommentDetailDO>().lambda();
        wrapper.eq(CommentDetailDO::getParentId, commentDetailDo.getParentId())
                .eq(CommentDetailDO::getCommentType, commentDetailDo.getCommentType());
        if (n > 0) {
            wrapper.last(" limit " + n);
        }
        return commentDetailMapper.selectList(wrapper);
    }
}