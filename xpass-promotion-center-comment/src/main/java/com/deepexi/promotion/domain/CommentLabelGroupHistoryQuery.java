package com.deepexi.promotion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.io.Serializable;


/**
 * @author
 */
@Data
@EqualsAndHashCode
public class CommentLabelGroupHistoryQuery implements Serializable {
    /**
     * 标签组ID
     */
    private Long labelGroupId;
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 当前页码
     */
    @Min(value = -1,message = "页码参数超范围")
    private Integer page;
    /**
     * 分页大小
     */
    @Min(value = 0,message = "分页大小超范围")
    private Integer size;
}

