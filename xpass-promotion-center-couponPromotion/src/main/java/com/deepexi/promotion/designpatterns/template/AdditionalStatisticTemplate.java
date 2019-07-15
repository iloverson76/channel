package com.deepexi.promotion.designpatterns.template;

import com.deepexi.promotion.domain.CommentStatisticDTO;
import com.deepexi.promotion.domain.CommentStatisticParamDTO;
import com.deepexi.promotion.enums.CommentStatisticTypeEnum;
import com.deepexi.promotion.enums.CommentTypeEnum;
import org.springframework.stereotype.Component;

/**
 * 追评统计模板(只需要统计追评，标签，图片和视频)
 *
 * @author liaoxiaoxin
 * @date 2019/6/12 19:44
 */
@Component
public class AdditionalStatisticTemplate extends AbstractStatisticTemplate {

    public AdditionalStatisticTemplate() {
        super();
        super.setType(CommentTypeEnum.ADDITIONAL_COMMENT.getCode());
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
        return false;
    }

    @Override
    CommentStatisticDTO buildStatisticWithCommentType(CommentStatisticParamDTO statisticDTO) {
        // 追评时，需要生成一条追评记录
        CommentStatisticDTO commentStatistic = statisticDTO.clone(CommentStatisticDTO.class);
        commentStatistic.setType(CommentStatisticTypeEnum.ADDITIONAL_COMMENT.getCode());
        // 设置统计编码
        buildStatisticCode(commentStatistic);
        return commentStatistic;
    }
}
