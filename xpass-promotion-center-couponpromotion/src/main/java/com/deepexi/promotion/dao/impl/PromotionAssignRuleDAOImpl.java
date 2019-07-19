package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.PromotionAssignRuleDAO;
import com.deepexi.promotion.domain.PromotionAssignRuleDO;
import com.deepexi.promotion.mapper.PromotionAssignRuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author zhoust
 * @date 2019/7/19
 **/
@Repository
public class PromotionAssignRuleDAOImpl extends ServiceImpl<PromotionAssignRuleMapper, PromotionAssignRuleDO> implements PromotionAssignRuleDAO {
    @Autowired
    PromotionAssignRuleMapper promotionAssignRuleMapper;

}
