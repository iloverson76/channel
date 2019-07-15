package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentInfoDAO;
import com.deepexi.promotion.domain.*;
import com.deepexi.promotion.mapper.CommentInfoMapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Repository
@Slf4j
public class CommentInfoDAOImpl extends ServiceImpl<CommentInfoMapper, CommentInfoDO> implements CommentInfoDAO {

    @Resource
    private CommentInfoMapper commentInfoMapper;

    @Override
    public List<CommentDetailDO> listByCommentInfoQuery(CommentInfoQuery query) {
        //是否分页
        Integer page = query.getPage();
        if ( page != null && page != -1) {
            PageHelper.startPage(page, query.getSize());
        }
        return commentInfoMapper.listByCommentInfoQuery(query);
    }

    @Override
    public long countComment(CommentCountQuery commentCountQuery) {
        return commentInfoMapper.countComment(commentCountQuery);
    }

    @Override
    public List<CommentDetailDO> listCommentByStatisticType(CommentStatisticTypeQuery query) {
        //是否分页
        Integer page = query.getPage();
        if ( page != null && page != -1) {
            PageHelper.startPage(page, query.getSize());
        }
        return commentInfoMapper.listCommentByStatisticType(query);
    }
}