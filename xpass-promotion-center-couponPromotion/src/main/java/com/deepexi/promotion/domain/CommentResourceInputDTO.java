package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评价资源，前端输入
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 11:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentResourceInputDTO extends AbstractObject {

    /**
     * 资源内容，图片则为图片地址，多个图片逗号分隔
     */
    private String resourceContent;

    /**
     * 资源类型 {@link com.deepexi.promotion.enums.CommentResourceTypeEnum}
     */
    private Integer resourceType;
}
