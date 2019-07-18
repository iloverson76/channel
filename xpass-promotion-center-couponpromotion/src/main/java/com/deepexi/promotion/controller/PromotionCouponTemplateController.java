package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.PromotionCouponTypeVO;
import com.deepexi.promotion.domain.template.CreateTemplateVO;
import com.deepexi.promotion.domain.template.TemplateInfoVO;
import com.deepexi.promotion.domain.template.TemplateQuery;
import com.deepexi.util.config.Payload;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 发布优惠券模板
     */
    @PostMapping("/publish")
    public void publish(@RequestBody CreateTemplateVO templateInfo) {

    }


    /**
     * 保存优惠券模板
     */
    @PostMapping("/save")
    public void save() {

    }

    /**
     * 获得已发布模板列表
     */
    @GetMapping("/list/published")
    public void listPublishedTemplate(TemplateQuery query) {




    }


    /**
     * 启用/禁用模板
     */
    @PutMapping("/{id}/enabled")
    public void listPublishedTemplate(@PathVariable(value = "id",required = true) Long id) {




    }


    /**
     * 获得未发布模板列表
     */
    @GetMapping("/list/unpublished")
    public void listUnpublishedTemplate(TemplateQuery query) {




    }

    @GetMapping("/info")
    public Payload<PromotionCouponTypeVO> couponTemplateInfo() {

        return new Payload(new TemplateInfoVO());

    }





    //新建优惠券模板:发布=>新建券实例



    //查询优惠券模板

    //删除优惠券模板(发放给活动的优惠券也用删除,然后再活动表里插入优惠券id,servic层的功能)

    //修改优惠券模板

    //停用/启用 优惠券模板



}
