package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.PromotionAssignRuleDTO;
import com.deepexi.promotion.domain.PromotionAssignRuleVO;
import com.deepexi.promotion.service.IPromotionAssignRuleService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 优惠发放规则表 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/api/v1/promotion/rules")
public class PromotionAssignRuleController  {
    @Autowired
    private IPromotionAssignRuleService promotionAssignRuleService;

    @PostMapping
    public Payload create(PromotionAssignRuleVO ruleVO){

        PromotionAssignRuleDTO ruleDTO = ruleVO.clone(PromotionAssignRuleDTO.class, CloneDirection.FORWARD);
        return new Payload(promotionAssignRuleService.create(ruleDTO));

    }

}
