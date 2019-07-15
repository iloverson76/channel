package com.deepexi.promotion.domain;

import lombok.*;

import javax.validation.constraints.Min;
import java.io.Serializable;


/**
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentBusinessModelConnectQuery implements Serializable {

    /**
     * 当前页码
     */
    @Min(value = -1,message = "页码参数超范围")
    private Integer page;
    /**
     * 分页大小
     */
    @Min(value = 0,message = "分页大小参数超范围")
    private Integer size;
    /**
     * 业务对象关联主键
     */
    private Long id;
    /**
     * 系统业务id
     */
    private Long  businessId;
    /**
     * 评价对象ID
     */
    private Long  modelId;
    /**
     * 应用id
     */
    private Long  appId;
    /**
     * 多租户标识
     */
    private String tenantId;
    /**
     * 是否包含回评
     */
    private Integer isReply;



}

