package com.deepexi.promotion.controller;

import com.deepexi.promotion.service.CommentStarService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(value = "/星级评价表", description = "$desc")
@RestController
@RequestMapping("/api/v1/comment/stars")
public class  CommentStarController {

    @Resource
    private CommentStarService commentStarService;}