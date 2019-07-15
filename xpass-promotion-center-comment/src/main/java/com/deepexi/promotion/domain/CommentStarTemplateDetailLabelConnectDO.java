package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/22
 **/
@TableName(value = "comment_star_template_detail_label_connect")
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class CommentStarTemplateDetailLabelConnectDO extends SuperEntity {
    /**
     * 星级评价详情Id
     */
    private Long starTemplateDetailId;
    /**
     * 标签模板Id
     */
    private Long labelTemplateId;
    /**
     * 应用Id
     */
    private Long appId;


    /**
     * 标签对象
     */
    @TableField(exist = false)
    private CommentLabelDetailDO label;
}
