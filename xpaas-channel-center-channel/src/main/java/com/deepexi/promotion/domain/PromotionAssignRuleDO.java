package com.deepexi.promotion.domain;

import lombok.*;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PromotionAssignRuleDO extends BaseEntity {

    private String ruleName;

    /**
     * 0用户规则，1商品规则，2渠道规则
     */
    private Integer ruleType;

    private String ruleCode;

    private String ruleDesc;

    private String parameterSrcAppName;

    private String parameterSrcApiName;

    private String parameterName;

    private String parameterCode;

    //备注
    private String remark;


}
