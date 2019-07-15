package com.deepexi.promotion.designpatterns.template;

import com.deepexi.promotion.domain.CommentStatisticDTO;
import com.deepexi.promotion.domain.CommentStatisticParamDTO;
import com.deepexi.promotion.enums.CommentStatisticTypeEnum;
import com.deepexi.promotion.enums.CommentTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 评价回复统计模板(只需要统计有一条回复)
 *
 * @author liaoxiaoxin
 * @date 2019/6/12 19:44
 */
@Component
public class ReplyStatisticTemplate extends AbstractStatisticTemplate {

    public ReplyStatisticTemplate() {
        super();
        super.setType(CommentTypeEnum.REPLY_COMMENT.getCode());
    }

    @Override
    boolean isCountLabel() {
        return false;
    }

    @Override
    boolean isCountResource() {
        return false;
    }

    @Override
    boolean isCountStar() {
        return false;
    }

    @Override
    CommentStatisticDTO buildStatisticWithCommentType(CommentStatisticParamDTO statisticDTO) {
        // 回复评论时，需要生成一条回复记录
        CommentStatisticDTO commentStatistic = statisticDTO.clone(CommentStatisticDTO.class);
        commentStatistic.setType(CommentStatisticTypeEnum.REPLY_COMMENT.getCode());
        // 设置统计编码
        buildStatisticCode(commentStatistic);
        return commentStatistic;
    }
}
