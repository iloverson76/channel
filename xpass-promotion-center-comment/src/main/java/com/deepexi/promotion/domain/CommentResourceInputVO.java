package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 评价资源，前端输入
 *
 * @author zhongzhouping
 * @version 1.0
 * @date 2019-05-08 11:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentResourceInputVO extends AbstractObject {

    /**
     * 资源内容，图片则为图片地址，多个图片逗号分隔
     */
    @NotBlank
    private String resourceContent;

    /**
     * 资源类型 {@link com.deepexi.promotion.enums.CommentResourceTypeEnum}
     */
    @NotNull
    private Integer resourceType;
}
