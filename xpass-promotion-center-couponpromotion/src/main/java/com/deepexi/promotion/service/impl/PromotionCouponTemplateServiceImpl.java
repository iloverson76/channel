package com.deepexi.promotion.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.promotion.dao.PromotionCouponTemplateDAO;
import com.deepexi.promotion.domain.template.PromotionCouponTemplateDO;
import com.deepexi.promotion.domain.coupon.CouponTypeDO;
import com.deepexi.promotion.domain.coupon.CouponTypeDTO;
import com.deepexi.promotion.mapper.PromotionCouponTemplateMapper;
import com.deepexi.promotion.service.IPromotionCouponTemplateService;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 优惠券模板表 服务实现类
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Service
public class PromotionCouponTemplateServiceImpl extends ServiceImpl<PromotionCouponTemplateMapper, PromotionCouponTemplateDO> implements IPromotionCouponTemplateService {

    @Autowired
    private PromotionCouponTemplateDAO promotionCouponTemplateDAO;

    @Override
    public List<CouponTypeDTO> getTypeList() {
        CouponTypeDO couponTypeDO = new CouponTypeDO();
        List<CouponTypeDO> typeList = promotionCouponTemplateDAO.getTypeList(couponTypeDO);
        return ObjectCloneUtils.convertList(typeList, CouponTypeDTO.class, CloneDirection.OPPOSITE);
    }
}
