package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentLabelAndResourceDAO;
import com.deepexi.promotion.dao.CommentLabelDAO;
import com.deepexi.promotion.domain.CommentLabelDO;
import com.deepexi.promotion.mapper.CommentLabelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author admin
 */
@Repository
@Slf4j
public class CommentLabelDAOImpl extends ServiceImpl<CommentLabelMapper, CommentLabelDO> implements CommentLabelDAO,
        CommentLabelAndResourceDAO<CommentLabelDO> {

    @Resource
    private CommentLabelMapper commentLabelMapper;
}