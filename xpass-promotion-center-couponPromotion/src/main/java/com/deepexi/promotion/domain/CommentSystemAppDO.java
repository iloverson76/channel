package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 
 */
@TableName("comment_system_app")
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentSystemAppDO extends SuperEntity {

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
    @TableField(value = "is_comment_check")
    private Boolean commentCheck;

    /**
     * 是否启用和禁用
     */
    @TableField(value = "is_enable")
    private Boolean enable;

}

