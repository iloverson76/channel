package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentDetailCheckDAO;
import com.deepexi.promotion.domain.CommentDetailCheckDO;
import com.deepexi.promotion.mapper.CommentDetailCheckMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author admin
 */
@Repository
@Slf4j
public class CommentDetailCheckDAOImpl extends
        ServiceImpl<CommentDetailCheckMapper, CommentDetailCheckDO> implements CommentDetailCheckDAO {

    @Resource
    private CommentDetailCheckMapper commentDetailCheckMapper;

    @Override
    public boolean insertBatch(List<CommentDetailCheckDO> detailCheckDOList) {
        return this.saveBatch(detailCheckDOList);
    }
}