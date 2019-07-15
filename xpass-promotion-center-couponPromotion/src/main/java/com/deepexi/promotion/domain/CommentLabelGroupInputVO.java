package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author 
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentLabelGroupInputVO extends AbstractObject implements Serializable {

    @NotBlank(message = "标签组名称不能为空")
    @ApiModelProperty(value = "标签组名称")
    private String name;

    @NotNull(message = "标签id不能为空")
    @ApiModelProperty(value = "标签id集合")
    private List<Long> labelIdsList;

}

