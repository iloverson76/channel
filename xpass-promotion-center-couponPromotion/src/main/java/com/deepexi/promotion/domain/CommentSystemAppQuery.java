package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;


/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentSystemAppQuery extends SuperEntity {
	
	/**
	 * 主键ID
	 */
	private Long id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 图标
     */
    private String logo;

    /**
     * 介绍
     */
    private String description;

    /**
     * 鉴权标识
     */
    private String secret;

    /**
     * 鉴权id
     */
    private String agentId;

    /**
     * 评价是否审核 0：否 1：是
     */
    private Boolean commentCheck;

    @Builder.Default
    @Min(value = -1,message = "页码不能小于-1")
    private Integer page = -1;

    @Min(value = 0,message = "页数不能小于0")
    private Integer size = 10;

    @ApiModelProperty(value = "是否启用 true是 false否")
    private Boolean enable;


}

