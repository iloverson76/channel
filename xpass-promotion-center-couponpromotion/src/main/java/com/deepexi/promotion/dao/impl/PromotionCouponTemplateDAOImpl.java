package com.deepexi.promotion.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.PromotionCouponTemplateDAO;
import com.deepexi.promotion.domain.template.PromotionCouponTemplateDO;
import com.deepexi.promotion.domain.coupon.CouponTypeDO;
import com.deepexi.promotion.mapper.PromotionCouponTemplateMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PromotionCouponTemplateDAOImpl extends ServiceImpl<PromotionCouponTemplateMapper, PromotionCouponTemplateDO> implements PromotionCouponTemplateDAO {


    @Override
    public List<CouponTypeDO> getTypeList(CouponTypeDO couponTypeDO) {
        return baseMapper.getTypeList(couponTypeDO);
    }
}
