package com.deepexi.promotion.designpatterns.strategy;

import com.deepexi.promotion.domain.CommentInfoQueryDTO;
import com.deepexi.promotion.domain.CommentStatisticTypeQuery;
import com.deepexi.promotion.enums.CommentStatisticTypeEnum;
import com.deepexi.promotion.enums.CommentTypeEnum;
import com.deepexi.promotion.service.CommentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 具体策略 -> 查询追加
 *
 * @author liaoxiaoxin
 * @date 2019/6/19 10:49
 */
@Component
@Slf4j
public class AdditionalQueryStrategy implements StatisticQueryStrategy {

    @Resource
    private CommentInfoService commentInfoService;

    @Override
    public Integer getType() {
        return CommentStatisticTypeEnum.ADDITIONAL_COMMENT.getCode();
    }

    @Override
    public List<CommentInfoQueryDTO> query(CommentStatisticTypeQuery statisticTypeQuery) {
        statisticTypeQuery.setCommentType(CommentTypeEnum.ADDITIONAL_COMMENT.getCode());
        return commentInfoService.listCommentByStatisticType(statisticTypeQuery);
    }
}
