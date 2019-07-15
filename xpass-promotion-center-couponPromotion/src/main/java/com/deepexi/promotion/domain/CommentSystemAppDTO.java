package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentSystemAppDTO extends AbstractObject {
	
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

    /**
     * 是否启用和禁用
     */
    private Boolean enable;
}

