package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.PromotionCouponTypeVO;
import com.deepexi.util.config.Payload;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 优惠券模板表 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/api/v1/promotion/template")
public class PromotionCouponTemplateController {


    /**
     * 获得模板类型
     */
    @GetMapping("/{appId:[a-zA-Z0-9,]+}/type")
    public Payload<PromotionCouponTypeVO> getType(@PathVariable(value = "appId", required = true) String appId) {

        return new Payload<>(new PromotionCouponTypeVO());
    }


}
