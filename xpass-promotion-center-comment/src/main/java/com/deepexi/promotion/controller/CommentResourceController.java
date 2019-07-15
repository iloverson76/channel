package com.deepexi.promotion.controller;

import com.deepexi.promotion.service.CommentResourceService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(value = "/评价资源表（文本、视频、图片）", description = "$desc")
@RestController
@RequestMapping("/api/v1/commentResources")
public class CommentResourceController {

    @Resource
    private CommentResourceService commentResourceService;

}