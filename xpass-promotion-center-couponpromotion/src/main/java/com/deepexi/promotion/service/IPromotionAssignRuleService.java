package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.PromotionAssignRuleDTO;
import com.deepexi.promotion.domain.PromotionAssignRuleQuery;

import java.util.List;
import java.util.Set;

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

    Boolean update(PromotionAssignRuleDTO ruleDTO);

    PromotionAssignRuleDTO detail(Long ruleId);

    List<PromotionAssignRuleDTO> findPage(PromotionAssignRuleQuery query);

    Boolean delete(Set<Long> ids);
}
