package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评价标签输入
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 11:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelInputDTO extends AbstractObject {

    /**
     * 标签id
     */
    private Long labelTemplateId;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 标签id
     */
    private Long labelGroupId;

}
