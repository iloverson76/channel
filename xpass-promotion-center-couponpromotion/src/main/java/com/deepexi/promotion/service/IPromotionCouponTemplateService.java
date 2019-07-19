package com.deepexi.promotion.service;

import com.deepexi.promotion.domain.template.PromotionCouponTemplateDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.template.CouponTypeDTO;

import java.util.List;

/**
 * <p>
 * 优惠券模板表 服务类
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
public interface IPromotionCouponTemplateService extends IService<PromotionCouponTemplateDO> {

    /**
     * 获得优惠券类型配置
     * @param couponTypeDO do
     * @return
     */
    List<CouponTypeDTO> getTypeList();
}
