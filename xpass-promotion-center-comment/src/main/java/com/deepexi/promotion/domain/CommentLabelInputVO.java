package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 评价标签输入
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 11:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelInputVO extends AbstractObject {

    /**
     * 标签id
     */
    @NotNull
    private Long labelTemplateId;

    /**
     * 标签名称
     */
    @NotNull
    private String labelName;

    /**
     * 标签id
     */
    private Long labelGroupId;

}
