package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.template.PromotionCouponTemplateDO;
import com.deepexi.promotion.domain.template.CouponTypeDO;

import java.util.List;

public interface PromotionCouponTemplateDAO extends  IService<PromotionCouponTemplateDO> {

    List<CouponTypeDO> getTypeList(CouponTypeDO couponTypeDO);
}
