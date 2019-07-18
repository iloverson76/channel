package com.deepexi.promotion.controller;


import com.deepexi.promotion.domain.coupon.UserCouponLinkDTO;
import com.deepexi.promotion.domain.coupon.UserCouponLinkVO;
import com.deepexi.promotion.service.IPromotionUserCouponLinkService;
import com.deepexi.util.config.Payload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 用户和优惠券关联表 前端控制器
 * </p>
 *
 * @author xsj
 * @since 2019-07-15
 */
@RestController
@RequestMapping("/api/v1/promotion/user/coupon/link")
public class PromotionUserCouponLinkController  {
    
    @Autowired
    IPromotionUserCouponLinkService iPromotionUserCouponLinkService;

    //用户领取
    @PostMapping("/get")
    public Payload<Boolean> get(@RequestBody UserCouponLinkVO userCoupLinkVO) {
        UserCouponLinkDTO userCoupLinkDto=userCoupLinkVO.clone(UserCouponLinkDTO.class);
//        return new Payload<Boolean>(iPromotionUserCouponLinkService.save(userCoupLinkDto));
        return null;
    }

    //用户转让/受让
    @PostMapping
    public Payload<Boolean> transfer(@RequestBody UserCouponLinkVO userCoupLinkVO) {
        UserCouponLinkDTO userCoupLinkDto=userCoupLinkVO.clone(UserCouponLinkDTO.class);
       // return new Payload<Boolean>(iPromotionUserCouponLinkService.update(userCoupLinkDto));
        return null;
    }
    //系统自动发放优惠券给用户
    @PostMapping
    public Payload<Boolean> autoAccepted(@RequestBody UserCouponLinkVO userCoupLinkVO) {
        UserCouponLinkDTO userCoupLinkDto=userCoupLinkVO.clone(UserCouponLinkDTO.class);
       // return new Payload<Boolean>(iPromotionUserCouponLinkService.save(userCoupLinkDto));
        return null;
    }

    /**
     *分页查询领用/使用情况
     */
    @GetMapping("/paging")
    //@ApiOperation(value = "分页查询", notes = "分页请求")
    public  Payload<UserCouponLinkVO> findPage(UserCouponLinkVO userCoupLinkVO,
                                               @RequestParam(value = "page", defaultValue = "0") Long page,
                                               @RequestParam(value = "size", defaultValue = "10") Long size) {
//        return new Payload(iPromotionCouponInstanceService.findPage(
//                userCoupLinkVO.clone(UserCouponLinkVO.class),page,size));
        return null;
    }






}
