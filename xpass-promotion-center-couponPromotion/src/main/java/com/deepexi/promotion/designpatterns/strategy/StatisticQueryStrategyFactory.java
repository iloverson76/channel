package com.deepexi.promotion.designpatterns.strategy;

import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 策略工厂：统计分类下的评论查询
 *
 * @author liaoxiaoxin
 * @date 2019/6/19 10:36
 */
@Component
@Slf4j
public class StatisticQueryStrategyFactory {

    private List<StatisticQueryStrategy> queryStrategyList;

    public StatisticQueryStrategyFactory(List<StatisticQueryStrategy> queryStrategyList) {
        this.queryStrategyList = queryStrategyList;
    }

    /**
     * 根据统计分类获取对应的查询策略类
     *
     * @param type 统计分类枚举code
     * @return 具体统计查询策略
     */
    public StatisticQueryStrategy getInstance(Integer type) {
        for (StatisticQueryStrategy queryStrategy : queryStrategyList) {
            if (queryStrategy.getType().equals(type)) {
                return queryStrategy;
            }
        }
        log.warn("获取不到统计分类查询策略类,参数type={}", type);
        throw new ApplicationException(ResultEnum.STATISTIC_QUERY_STRATEGY_NOT_FOUND);
    }
}
