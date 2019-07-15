package com.deepexi.promotion.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.deepexi.util.pojo.AbstractObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName CommentSystemAppInputVO
 * @Description TODO
 * @Author zhoujiawen
 * @Date 2019-05-07 19:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("应用录入dto")
public class CommentSystemAppInputVO extends AbstractObject {

    @NotBlank(message = "应用名称不能为空")
    @ApiModelProperty(value = "应用名称")
    private String name;

    @NotBlank(message = "应用logo不能为空")
    @ApiModelProperty(value = "应用图标")
    private String logo;

    @ApiModelProperty(value = "应用描述")
    private String description;

    @NotNull(message = "是否审核不能为空")
    @ApiModelProperty(value = "是否审核，true是 ，false否")
    private Boolean commentCheck;

    @ApiModelProperty(value = "是否启用，true是 ，false否")
    private Boolean enable;

}
