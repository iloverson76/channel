package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.PromotionAssignRuleDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.PromotionAssignRuleDTO;

/**
 * <p>
 * 优惠发放规则表 服务类
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
public interface IPromotionAssignRuleService {

    Boolean create(PromotionAssignRuleDTO ruleDTO);
}
