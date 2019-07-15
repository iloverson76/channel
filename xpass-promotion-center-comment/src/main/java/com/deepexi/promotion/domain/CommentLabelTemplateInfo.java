package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelTemplateInfo extends AbstractObject {

    private Long id;
    /**
     * 名称
     */
    @NotEmpty(message = "标签名不能为空")
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
    @NotEmpty(message = "应用Id不能为空")
    private String appId;
}
