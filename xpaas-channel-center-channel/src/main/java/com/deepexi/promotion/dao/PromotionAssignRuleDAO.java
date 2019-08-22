package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.PromotionAssignRuleDO;
import com.deepexi.promotion.domain.PromotionAssignRuleQuery;

import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/7/19
 **/
public interface PromotionAssignRuleDAO extends IService<PromotionAssignRuleDO> {
    List<PromotionAssignRuleDO> getByRuleIds(Set<Long> ids);

    List<PromotionAssignRuleDO> findPage(PromotionAssignRuleQuery query);
}
