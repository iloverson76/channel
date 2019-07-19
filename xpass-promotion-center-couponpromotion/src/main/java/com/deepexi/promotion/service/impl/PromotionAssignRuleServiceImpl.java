package com.deepexi.promotion.service.impl;

import com.deepexi.promotion.dao.PromotionAssignRuleDAO;
import com.deepexi.promotion.domain.PromotionAssignRuleDO;
import com.deepexi.promotion.domain.PromotionAssignRuleDTO;
import com.deepexi.promotion.mapper.PromotionAssignRuleMapper;
import com.deepexi.promotion.service.IPromotionAssignRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠发放规则表 服务实现类
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Service
public class PromotionAssignRuleServiceImpl implements IPromotionAssignRuleService {

    @Autowired
    private PromotionAssignRuleDAO promotionAssignRuleDAO;

    @Override
    public Boolean create(PromotionAssignRuleDTO ruleDTO) {

        PromotionAssignRuleDO ruleDO = ruleDTO.clone(PromotionAssignRuleDO.class);

        return promotionAssignRuleDAO.save(ruleDO);
    }
}
