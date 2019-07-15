package com.deepexi.promotion.domain;

import lombok.Data;

/**
 * @author zhangyanping
 * @date 2019/5/29 14:17
 */
@Data
public class CommentBusinessTemplateSetQuery {
    /**
     * 文本设置
     */
    private Long[] connectIds;
    /**
     * 评论类型
     */
    private Integer type;
    /**
     * 业务ID
     */
    private Long businessId;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 根据评价对象查询
     */
    private Long modelId;
    /**
     * 是否发布 是
     */
    private Boolean isPublishing;
}
