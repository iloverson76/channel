package com.deepexi.promotion.mapper;

import com.deepexi.promotion.domain.PromotionCouponTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.promotion.domain.coupon.CouponTypeDO;

import java.util.List;

/**
 * <p>
 * 优惠券模板表 Mapper 接口
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
public interface PromotionCouponTemplateMapper extends BaseMapper<PromotionCouponTemplate> {

    List<CouponTypeDO> getTypeList(CouponTypeDO couponTypeDO);
}
