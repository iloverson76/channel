package com.deepexi.promotion.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.promotion.domain.coupon.CouponInstance;
import com.deepexi.promotion.domain.coupon.CouponInstanceDTO;

/**
 * <p>
 * 优惠券实例表 服务类
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
public interface ICouponInstanceService extends IService<CouponInstance> {

    /**
     * 批量创建优惠券实例
     * @param couponInstanceDto
     * @return
     */
    public boolean create(CouponInstanceDTO couponInstanceDto);
}
