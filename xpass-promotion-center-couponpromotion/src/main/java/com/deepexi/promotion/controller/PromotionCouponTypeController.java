package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.coupon.CouponTypeDTO;
import com.deepexi.promotion.domain.coupon.CouponTypeVO;
import com.deepexi.promotion.service.IPromotionCouponTypeService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@RestController
@RequestMapping("/api/v1/promotion/coupon/type")
public class PromotionCouponTypeController {

    @Autowired
    IPromotionCouponTypeService iPromotionCouponTypeService;

    /**
     * 新建优惠券类型
     */
    @PostMapping
    public Payload<Boolean> create(@RequestBody CouponTypeVO coupTypeVO) {
        CouponTypeDTO coupTypeDto=coupTypeVO.clone(CouponTypeDTO.class);
        return new Payload<Boolean>(iPromotionCouponTypeService.save(coupTypeDto));
    }
    /**
     *分页查询优惠券类型
     */
    @GetMapping
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CouponTypeVO coupTypeVO,
                             @RequestParam(value = "page", defaultValue = "0") Long page,
                             @RequestParam(value = "size", defaultValue = "10") Long size) {
        return new Payload(iPromotionCouponTypeService.findPage(coupTypeVO.clone(CouponTypeDTO.class),
                page, size));
    }

    /**
     *优惠券类型详情
     */
    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Long  pk) {
        return new Payload(iPromotionCouponTypeService.detail(pk));
    }

    /**
     * 编辑优惠券类型(包括停用)
     */
    //@ApiOperation(value = "根据id修改", notes = "根据id修改优惠券实例")
    public Payload update(@PathVariable(value = "id", required = true) Long  pk, @RequestBody CouponTypeVO copuTypeVO) {
        copuTypeVO.setId(pk);
        return new Payload(iPromotionCouponTypeService.update(pk, copuTypeVO.clone(CouponTypeDTO.class)));
    }

    /**
     * 删除优惠券类型
     * @param pk
     * @return
     */
    @DeleteMapping("/{id}")
    @Transactional
    //@ApiOperation(value = "根据id删除OrderSaleItem", notes = "根据id删除优惠券实例")
    public Payload delete(@PathVariable(value = "id", required = true) Long  pk) {
        return new Payload(iPromotionCouponTypeService.removeById(pk));
    }

    /**
     * 批量删除优惠券实例
     * @param ids
     * @return
     */
    @DeleteMapping
    @Transactional
    //@ApiOperation(value = "根据id批量删除优惠券实例", notes = "根据id批量删除优惠券实例")
    public Payload delete(@RequestParam(required = true) Long [] ids) {
        return new Payload(iPromotionCouponTypeService.removeByIds(Arrays.asList(ids)));
    }



}
