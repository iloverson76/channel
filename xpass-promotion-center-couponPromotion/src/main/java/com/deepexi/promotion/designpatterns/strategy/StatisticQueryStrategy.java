package com.deepexi.promotion.designpatterns.strategy;

import com.deepexi.promotion.domain.CommentInfoQueryDTO;
import com.deepexi.promotion.domain.CommentStatisticTypeQuery;

import java.util.List;

/**
 * 统计分类下评论的查询策略
 *
 * @author liaoxiaoxin
 * @date 2019/6/19 10:27
 */
public interface StatisticQueryStrategy {

    /**
     * 统计类型
     * @return 统计类型 {@link com.deepexi.promotion.enums.CommentStatisticTypeEnum}
     */
    Integer getType();

    /**
     * 查询具体统计分类下的所有评论内容
     *
     * @param statisticTypeQuery 查询参数
     * @return 具体分类下的评论内容
     */
    List<CommentInfoQueryDTO> query(CommentStatisticTypeQuery statisticTypeQuery);
}
