package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.coupon.CouponInstanceDTO;
import com.deepexi.promotion.domain.coupon.CouponInstanceVO;
import com.deepexi.promotion.service.ICouponInstanceService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 优惠券实例表 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/api/v1/promotion/coupon/instance")
public class CouponInstanceController {

    @Autowired
    ICouponInstanceService iCouponInstanceService;

    /**
     * 生成优惠券实例(在模板里生成)--调用service里的接口
     * @param
     * @return
     */
    @PostMapping("/create")
    public Payload<Boolean> create(@RequestBody CouponInstanceVO coupInstVO) {
        CouponInstanceDTO couponInstanceDto=coupInstVO.clone(CouponInstanceDTO.class);
        boolean result=iCouponInstanceService.create(couponInstanceDto);
        return new Payload<Boolean>(result);
    }

    /**
     *分页查询优惠券实例--只用到service接口
     */
    @GetMapping("/paging")
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload findPage(CouponInstanceVO copuInstVO,
                             @RequestParam(value = "page", defaultValue = "0") Long page,
                             @RequestParam(value = "size", defaultValue = "10") Long size) {
//        return new Payload(iCouponInstanceService.findPage(copuInstVO.clone(CouponInstanceDTO.class),
//                page, size));
        return null;
    }
    /**
     *优惠券实例详情
     */
    @GetMapping("/{id}")
    public Payload detail(@PathVariable(value = "id", required = true) Long  pk) {
//        return new Payload(iCouponInstanceService.detail(pk));
        return null;
    }



    /**
     * 删除优惠券实例
     * @param pk
     * @return
     */
    @DeleteMapping("/{id}")
    @Transactional
    //@ApiOperation(value = "根据id删除OrderSaleItem", notes = "根据id删除优惠券实例")
    public Payload delete(@PathVariable(value = "id", required = true) Long  pk) {
//        return new Payload(iCouponInstanceService.removeById(pk));
        return null;
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
        //return new Payload(iCouponInstanceService.removeByIds(Arrays.asList(ids)));
        return null;
    }

    /**
     * 编辑优惠券实例
     */
    //@ApiOperation(value = "根据id修改", notes = "根据id修改优惠券实例")
    public Payload update(@PathVariable(value = "id", required = true) Long  pk, @RequestBody CouponInstanceVO copuInstVO) {
        copuInstVO.setId(pk);
      //  return new Payload(iCouponInstanceService.update(pk, copuInstVO.clone(CouponInstanceDTO.class)));
        return null;
    }
    /**
     * 转让优惠券
     */

    /**
     * 核销优惠券
     */

    /**
     *
     */
    /**
     * 退回
     */

    /**
     * 锁定
     */

    /**
     * 回收优惠券?
     */

    /**
     * 停用
     */


}
