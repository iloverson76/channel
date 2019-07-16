package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.coupon.PromotionCouponInstance;
import com.deepexi.promotion.domain.coupon.PromotionCouponInstanceDTO;
import com.deepexi.promotion.domain.coupon.PromotionCouponInstanceVO;
import com.deepexi.promotion.service.IPromotionCouponInstanceService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 优惠券实例表 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/promotion-coupon-instance")
public class PromotionCouponInstanceController  {

    @Autowired
    IPromotionCouponInstanceService iPromotionCouponInstanceService;

    /**
     * 生成优惠券实例(在模板里生成)--调用service里的接口
     * @param
     * @return
     */

    @PostMapping
    public Payload<Boolean> create(@RequestBody PromotionCouponInstanceVO copuInstVO) {
        PromotionCouponInstanceDTO couponInstanceDto=copuInstVO.clone(PromotionCouponInstanceDTO.class);
        return new Payload<Boolean>(iPromotionCouponInstanceService.create(couponInstanceDto));
    }

    //查询优惠券实例

    //删除优惠券实例

    //转让优惠券

    //核销优惠券

    //回收优惠券
}
