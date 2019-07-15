package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.*;


/**
 * 评价标签表
 *
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@TableName("comment_label")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentLabelDO extends SuperEntity  {

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 关联标签id
     */
    private Long labelTemplateId;

    /**
     * 关联标签组id
     */
    private Long labelGroupId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 评价明细或星评评价表id，根据relevance_type确定
     */
    private Long relevanceId;

    /**
     * 关联类型（1：评价明细，2：星评评价）
     */
    private Integer relevanceType;

}

