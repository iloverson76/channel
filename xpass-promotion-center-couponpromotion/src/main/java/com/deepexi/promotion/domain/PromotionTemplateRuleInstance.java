package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券模板和自定义规则关联表
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionTemplateRuleInstance对象", description="优惠券模板对应自定义规则")
public class PromotionTemplateRuleInstance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "模板")
    private Long templateId;

    @ApiModelProperty(value = "优惠券自定义规则")
    private Long assignRuleId;

    private String ruleValue;


}
