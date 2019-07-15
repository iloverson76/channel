package com.deepexi.promotion.controller;

import com.deepexi.promotion.domain.CommentInfoQueryVO;
import com.deepexi.promotion.domain.CommentStatisticQuery;
import com.deepexi.promotion.domain.CommentStatisticTypeQuery;
import com.deepexi.promotion.domain.CommentStatisticVO;
import com.deepexi.promotion.extension.AppRuntimeEnv;
import com.deepexi.promotion.service.CommentStatisticService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author liaoxx
 */
@Api(value = "/评价统计表")
@RestController
@RequestMapping("/api/v1/comment/statistics")
public class  CommentStatisticController {

    @Resource
    private CommentStatisticService statisticService;

    @Resource
    private AppRuntimeEnv appRuntimeEnv;

    @GetMapping
    public Payload listStatistic(@Valid CommentStatisticQuery statisticQuery) {
        statisticQuery.setTenantId(appRuntimeEnv.getTenantId());
        return new Payload<>(ObjectCloneUtils.convertList(
                statisticService.listStatistics(statisticQuery), CommentStatisticVO.class));
    }

    @GetMapping(value = "/list")
    public Payload listCommentByStatisticType(@Validated CommentStatisticTypeQuery statisticTypeQuery) {
        statisticTypeQuery.setTenantId(appRuntimeEnv.getTenantId());
        List<CommentInfoQueryVO> commentList = ObjectCloneUtils.convertList(
                statisticService.listCommentByStatisticType(statisticTypeQuery),
                CommentInfoQueryVO.class, CloneDirection.OPPOSITE);
        return new Payload<>(new PageBean<>(commentList));
    }
}