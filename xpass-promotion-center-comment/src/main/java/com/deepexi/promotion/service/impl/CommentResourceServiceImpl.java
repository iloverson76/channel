package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentResourceDAO;
import com.deepexi.promotion.domain.CommentResourceDO;
import com.deepexi.promotion.mapper.CommentResourceMapper;
import com.deepexi.promotion.service.CommentResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 */
@Service
@Slf4j
public class CommentResourceServiceImpl
        extends ServiceImpl<CommentResourceMapper, CommentResourceDO> implements CommentResourceService {

    @Resource
    private CommentResourceDAO commentResourceDAO;


}