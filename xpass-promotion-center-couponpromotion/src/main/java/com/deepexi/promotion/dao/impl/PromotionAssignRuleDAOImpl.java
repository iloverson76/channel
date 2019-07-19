package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.PromotionAssignRuleDAO;
import com.deepexi.promotion.domain.PromotionAssignRuleDO;
import com.deepexi.promotion.domain.PromotionAssignRuleQuery;
import com.deepexi.promotion.mapper.PromotionAssignRuleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zhoust
 * @date 2019/7/19
 **/
@Repository
public class PromotionAssignRuleDAOImpl extends ServiceImpl<PromotionAssignRuleMapper, PromotionAssignRuleDO> implements PromotionAssignRuleDAO {
    @Autowired
    PromotionAssignRuleMapper promotionAssignRuleMapper;

    @Override
    public List<PromotionAssignRuleDO> getByRuleIds(Set<Long> ids) {
        if (CollectionUtils.isEmpty(ids)){
            return new ArrayList<>();
        }
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("id",ids);
        return promotionAssignRuleMapper.selectList(queryWrapper);
    }

    @Override
    public List<PromotionAssignRuleDO> findPage(PromotionAssignRuleQuery query) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("app_id",query.getAppId());
        if (StringUtils.isNotEmpty(query.getRuleName())){
            queryWrapper.like("rule_name",query.getRuleName());
        }
        if (StringUtils.isNotEmpty(query.getRuleCode())){
            queryWrapper.like("rule_code",query.getRuleCode());
        }
        if (StringUtils.isNotEmpty(query.getParameterCode())){
            queryWrapper.like("parameter_code",query.getParameterCode());
        }
        queryWrapper.orderByDesc("id");
        return promotionAssignRuleMapper.selectList(queryWrapper);
    }
}
