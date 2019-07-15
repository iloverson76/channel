package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/5/23
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelTemplateNameInfo extends AbstractObject {
    private String id;
    private String code;
    private String name;
}
