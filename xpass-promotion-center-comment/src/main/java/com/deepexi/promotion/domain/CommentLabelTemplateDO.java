package com.deepexi.promotion.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.deepexi.promotion.domain.SuperEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
@TableName("comment_label_template")
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelTemplateDO extends SuperEntity {

    /**
     * 名称
     */
    private String name;
    /**
     * 编号
     */
    private String code;
    /**
     * 类型：0系统标签  1手工标签
     */
    private Integer type;

    /**
     * 应用Id
     */
    private String appId;



}
