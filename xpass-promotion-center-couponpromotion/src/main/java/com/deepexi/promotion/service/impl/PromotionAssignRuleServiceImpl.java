package com.deepexi.promotion.service.impl;

import com.deepexi.promotion.dao.PromotionAssignRuleDAO;
import com.deepexi.promotion.domain.PromotionAssignRuleDO;
import com.deepexi.promotion.domain.PromotionAssignRuleDTO;
import com.deepexi.promotion.domain.PromotionAssignRuleQuery;
import com.deepexi.promotion.service.IPromotionAssignRuleService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
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

    @Override
    public Boolean update(PromotionAssignRuleDTO ruleDTO) {

        PromotionAssignRuleDO ruleDO = ruleDTO.clone(PromotionAssignRuleDO.class);
        return promotionAssignRuleDAO.updateById(ruleDO);
    }

    @Override
    public PromotionAssignRuleDTO detail(Long ruleId) {
        List<PromotionAssignRuleDO> rules = promotionAssignRuleDAO.getByRuleIds(Collections.singleton(ruleId));
        if (CollectionUtils.isEmpty(rules)) {
            return null;
        }
        return rules.get(0).clone(PromotionAssignRuleDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public List<PromotionAssignRuleDTO> findPage(PromotionAssignRuleQuery query) {
        if (query.isPage()) {
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        //这个list将会是com.github.pagehelper.Page这个类，因为上面开启了分页查询
        List<PromotionAssignRuleDO> ruleDOList = promotionAssignRuleDAO.findPage(query);
        return ObjectCloneUtils.convertList(ruleDOList, PromotionAssignRuleDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean delete(Set<Long> ids) {
        return promotionAssignRuleDAO.removeByIds(ids);
    }


}
