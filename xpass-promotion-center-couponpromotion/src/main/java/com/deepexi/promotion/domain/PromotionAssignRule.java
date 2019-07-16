package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠发放规则表
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionAssignRule对象", description="优惠发放规则表")
public class PromotionAssignRule extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠券自定义规则名称")
    private String ruleName;

    @ApiModelProperty(value = "规则类型")
    private Integer ruleType;

    @ApiModelProperty(value = "规则代码")
    private String ruleCode;

    @ApiModelProperty(value = "参数来源")
    private String parameterSrc;

    @ApiModelProperty(value = "参数名称")
    private String parameterName;

    @ApiModelProperty(value = "参数代码")
    private String parameterCode;

    @ApiModelProperty(value = "备注")
    private String remark;


}
