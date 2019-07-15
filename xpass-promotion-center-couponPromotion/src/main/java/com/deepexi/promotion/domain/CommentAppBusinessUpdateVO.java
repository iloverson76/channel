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
public class CommentAppBusinessUpdateVO extends AbstractObject {

    @NotBlank(message = "业务名称不能为空！")
    @ApiModelProperty(value = "业务名称")
    private String name;

    @NotBlank(message = "标识码不能为空")
    @ApiModelProperty(value = "标识码")
    private String identificationCode;

//    @NotNull(message = "是否启用不能为空")
    @ApiModelProperty(value = "是否启用")
    private Boolean enable;

    @NotNull(message = "应用id不能为空")
    @ApiModelProperty(value = "应用id")
    private Long appId;
}
