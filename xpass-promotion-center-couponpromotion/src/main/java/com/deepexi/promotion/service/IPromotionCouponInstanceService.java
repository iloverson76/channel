package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.coupon.PromotionCouponInstanceDTO;

/**
 * <p>
 * 优惠券实例表 服务类
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
public interface IPromotionCouponInstanceService extends IService<PromotionCouponInstanceDTO> {

    /**
     * 创建实例
     *
     * @param dto
     * @return
     */
   public Boolean create(PromotionCouponInstanceDTO coupInstDTO);

}
