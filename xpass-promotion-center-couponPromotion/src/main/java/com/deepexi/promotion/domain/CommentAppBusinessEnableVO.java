package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName CommentAppBusinessUpdateVO
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-05-13 15:19
 */
@Data
@ApiModel("编辑业务")
public class CommentAppBusinessEnableVO extends AbstractObject {

    @NotNull(message = "是否启用不能为空")
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;
}
