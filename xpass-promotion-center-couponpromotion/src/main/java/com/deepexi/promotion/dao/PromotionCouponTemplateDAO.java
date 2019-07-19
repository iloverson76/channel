package com.deepexi.promotion.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.PromotionCouponTemplate;
import com.deepexi.promotion.domain.coupon.CouponTypeDO;

import java.util.List;

public interface PromotionCouponTemplateDAO extends  IService<PromotionCouponTemplate> {

    List<CouponTypeDO> getTypeList(CouponTypeDO couponTypeDO);
}
