package com.deepexi.promotion.designpatterns.template;

import com.deepexi.promotion.enums.CommentTypeEnum;
import com.deepexi.promotion.enums.ResultEnum;
import com.deepexi.util.extension.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 评论统计模板工厂
 *
 * @author liaoxiaoxin
 * @date 2019/6/13 10:17
 */
@Component
@Slf4j
public class StatisticTemplateFactory {

    @Autowired
    private List<AbstractStatisticTemplate> statisticTemplateList;

    /**
     * 根据评论类型获取统计模板类
     * COMMENT -> PublishStatisticTemplate
     * ADDITIONAL_COMMENT -> AdditionalStatisticTemplate
     * REPLY_COMMENT -> ReplyStatisticTemplate
     *
     * @param commentTypeEnum {@link CommentTypeEnum}
     * @return 具体统计模板类
     */
    public AbstractStatisticTemplate getInstance(CommentTypeEnum commentTypeEnum) {
        for (AbstractStatisticTemplate statisticTemplate : statisticTemplateList) {
            if (statisticTemplate.getType().equals(commentTypeEnum.getCode())) {
                return statisticTemplate;
            }
        }
        log.warn("获取不到统计模板类,参数：{}", commentTypeEnum.getCode());
        throw new ApplicationException(ResultEnum.STATISTIC_TEMPLATE_NOT_FOUND);
    }


}
