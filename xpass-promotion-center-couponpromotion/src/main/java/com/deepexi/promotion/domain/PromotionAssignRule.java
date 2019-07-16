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
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionAssignRule对象", description="优惠发放规则表")
public class PromotionAssignRule extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "优惠券自定义规则名称")
    private String ruleName;

    @ApiModelProperty(value = "规则类型，USER用户规则，GOOD商品规则，CHANNEL渠道规则")
    private String ruleType;

    @ApiModelProperty(value = "规则代码")
    private String ruleCode;

    @ApiModelProperty(value = "参数来源")
    private String parameterSrcDatabase;

    private String parameterSrcTable;

    @ApiModelProperty(value = "参数名称如用户名称")
    private String parameterName;

    @ApiModelProperty(value = "参数编码如user_id")
    private String parameterCode;

    @ApiModelProperty(value = "备注")
    private String remark;


}
