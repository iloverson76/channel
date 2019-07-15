package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@TableName(value = "comment_star_template_history")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CommentStarTemplateHistoryDO extends SuperEntity {
    private Long starTemplateId;
    private String starTemplateName;
    private String history;
    private Long appId;
}
