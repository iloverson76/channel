package com.deepexi.promotion.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deepexi.util.pojo.AbstractObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName CommentSystemAppEnbaleCheckVO
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-05-08 15:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentSystemAppEnbaleCheckVO extends AbstractObject {

    @NotNull(message = "是否开启评价审核不能为空")
    private Boolean commentCheck;
}
