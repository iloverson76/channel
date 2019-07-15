package com.deepexi.promotion.domain;


import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;


/**
 * @author zhangyanping
 * @date 2019/5/8 17:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CommentModelQuery extends AbstractObject {

    /**
     * 应用id
     */
    private Long  appId;
    /**
     * 名称
     */
    private String name;
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 启用状态
     */
    private Integer isEnable;
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

