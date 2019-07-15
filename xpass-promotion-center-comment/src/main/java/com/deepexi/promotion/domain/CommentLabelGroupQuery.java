package com.deepexi.promotion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;


/**
 * @author
 */
@Data
@EqualsAndHashCode
public class CommentLabelGroupQuery implements Serializable {
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

    /**
     * 标签名称查询
     */
    private String  name;

    /**
     * 应用ID
     */
    private Long appId;

    /**
     * 标签名
     */
    private String labelName;

    /**
     * 分组ID
     */
    private Long groupId;
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 批量查
     */
    private Long [] ids;


}

