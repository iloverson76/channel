package com.deepexi.promotion.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/16
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelTemplateDTO extends CommentLabelTemplateInfo {

    /**
     * 版本号，乐观锁
     */
    private Integer  version;
    /**
     * 备注，一些必要备注，如删除时，为什么删除
     */
    private String remark;
}
