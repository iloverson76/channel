package com.deepexi.promotion.controller;


import com.deepexi.promotion.service.IPromotionCouponStatisticsService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 优惠券实例统计表 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/api/v1/promotion/coupon/statistics")
public class PromotionCouponStatisticsController {

    @Autowired
    IPromotionCouponStatisticsService iPromotionCouponStatisticsService;

    /**
     *优惠券实例详情
     */
    @GetMapping("/{status}")
    public Payload detail() {
        return new Payload(iPromotionCouponStatisticsService.detail());
    }
}
