package com.deepexi.promotion.domain;

import com.deepexi.promotion.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动和优惠劵发放规则关联表
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionActivityAssignRuleLink对象", description="活动和优惠劵发放规则关联表")
public class PromotionActivityAssignRuleLink extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "活动")
    private String activityId;

    @ApiModelProperty(value = "优惠券发放规则")
    private String assignRuleId;


}
