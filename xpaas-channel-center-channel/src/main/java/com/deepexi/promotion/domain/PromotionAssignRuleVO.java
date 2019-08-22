package com.deepexi.promotion.domain;

import com.deepexi.util.pojo.AbstractObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhoust
 * @date 2019/7/19
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class PromotionAssignRuleVO extends AbstractObject {

    private Long id;

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
