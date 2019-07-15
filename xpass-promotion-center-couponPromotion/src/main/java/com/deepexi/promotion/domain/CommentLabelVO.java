package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelVO extends AbstractObject {

    /**
     * 主键ID
     */
    private Long id;

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

