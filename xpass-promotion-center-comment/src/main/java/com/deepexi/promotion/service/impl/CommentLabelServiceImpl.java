package com.deepexi.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentLabelDAO;
import com.deepexi.promotion.domain.CommentLabelDO;
import com.deepexi.promotion.mapper.CommentLabelMapper;
import com.deepexi.promotion.service.CommentLabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 */
@Service
@Slf4j
public class CommentLabelServiceImpl
        extends ServiceImpl<CommentLabelMapper, CommentLabelDO> implements CommentLabelService {

    @Resource
    private CommentLabelDAO commentLabelDAO;

}