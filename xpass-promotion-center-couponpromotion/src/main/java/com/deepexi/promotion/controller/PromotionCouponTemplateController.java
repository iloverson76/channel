package com.deepexi.promotion.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 优惠券模板表 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/promotion-coupon-template")
public class PromotionCouponTemplateController  {


    //新建优惠券模板:保存=>不发布券实例(草稿)

    //新建优惠券模板:发布=>新建券实例



    //查询优惠券模板

    //删除优惠券模板(发放给活动的优惠券也用删除,然后再活动表里插入优惠券id,servic层的功能)

    //修改优惠券模板

    //停用/启用 优惠券模板



}
