package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @ClassName CommentSystemAppEnableVO
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-07-02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentSystemAppEnableVO extends AbstractObject {

    @NotNull(message = "是否开启应用")
    private Boolean enable;
}
