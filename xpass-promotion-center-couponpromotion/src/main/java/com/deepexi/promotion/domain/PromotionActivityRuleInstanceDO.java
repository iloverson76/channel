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
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="PromotionActivityRuleInstanceDO对象", description="活动和优惠劵发放规则关联表")
public class PromotionActivityRuleInstanceDO extends BaseEntity {



    @ApiModelProperty(value = "活动id")
    private Long activityId;

    @ApiModelProperty(value = "优惠券发放规则id")
    private Long assignRuleId;

    private String ruleValue;


}
