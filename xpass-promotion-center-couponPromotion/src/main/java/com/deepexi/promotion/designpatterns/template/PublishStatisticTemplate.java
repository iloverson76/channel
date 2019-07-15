package com.deepexi.promotion.designpatterns.template;

import com.deepexi.promotion.domain.CommentStatisticDTO;
import com.deepexi.promotion.domain.CommentStatisticParamDTO;
import com.deepexi.promotion.enums.CommentStatisticTypeEnum;
import com.deepexi.promotion.enums.CommentTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 评价发布统计模板(需要统计评论,星评，标签，图片和资源)
 *
 * @author liaoxiaoxin
 * @date 2019/6/12 19:44
 */
@Component
public class PublishStatisticTemplate extends AbstractStatisticTemplate {

    public PublishStatisticTemplate() {
        super();
        super.setType(CommentTypeEnum.COMMENT.getCode());
    }

    @Override
    boolean isCountLabel() {
        return true;
    }

    @Override
    boolean isCountResource() {
        return true;
    }

    @Override
    boolean isCountStar() {
        return true;
    }

    @Override
    CommentStatisticDTO buildStatisticWithCommentType(CommentStatisticParamDTO statisticDTO) {
        // 发布评价时统计评论数+1
        CommentStatisticDTO commentStatistic = statisticDTO.clone(CommentStatisticDTO.class);
        commentStatistic.setType(CommentStatisticTypeEnum.COMMENT.getCode());
        // 设置统计编码
        buildStatisticCode(commentStatistic);
        return commentStatistic;
    }
}
