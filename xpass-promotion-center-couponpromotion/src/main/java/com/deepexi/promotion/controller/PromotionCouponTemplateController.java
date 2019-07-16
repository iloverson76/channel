package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.PromotionCouponTypeVO;
import com.deepexi.util.config.Payload;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
    @GetMapping("/type")
    public Payload<List<PromotionCouponTypeVO>> couponTemplateType() {
        return new Payload<>(Lists.newArrayList(new PromotionCouponTypeVO()));
    }



    //新建优惠券模板:保存=>不发布券实例(草稿)

    //新建优惠券模板:发布=>新建券实例



    //查询优惠券模板

    //删除优惠券模板(发放给活动的优惠券也用删除,然后再活动表里插入优惠券id,servic层的功能)

    //修改优惠券模板

    //停用/启用 优惠券模板



}
