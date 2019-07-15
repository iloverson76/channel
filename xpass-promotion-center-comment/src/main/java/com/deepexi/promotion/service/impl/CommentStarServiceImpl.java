package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.CommentStarDAO;
import com.deepexi.promotion.domain.CommentStarDO;
import com.deepexi.promotion.mapper.CommentStarMapper;
import com.deepexi.promotion.service.CommentStarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 */
@Service
@Slf4j
public class CommentStarServiceImpl extends ServiceImpl<CommentStarMapper, CommentStarDO> implements CommentStarService {

    @Resource
    private CommentStarDAO commentStarDAO;
}