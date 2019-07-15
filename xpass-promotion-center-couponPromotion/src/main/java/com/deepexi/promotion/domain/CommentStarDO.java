package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.*;


/**
 * 星级评价表
 *
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@TableName("comment_star")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentStarDO extends SuperEntity {

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 关联评价明细id
     */
    private Long commentDetailId;

    /**
     * 关联星评评级id
     */
    private Long starTemplateId;

    /**
     * 星评值
     */
    private Integer starValue;

    /**
     * 标签名称
     */
    private String starName;

    /**
     * 星评模板明细ID
     */
    private Long starTemplateDetailId;


}

