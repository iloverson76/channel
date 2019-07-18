package com.deepexi.promotion.service.impl;

import com.deepexi.promotion.domain.coupon.CouponInstance;
import com.deepexi.promotion.domain.coupon.CouponInstanceDTO;
import com.deepexi.promotion.mapper.CouponInstanceMapper;
import com.deepexi.promotion.service.ICouponInstanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Service
public class CouponInstanceServiceImpl extends ServiceImpl<CouponInstanceMapper, CouponInstance> implements ICouponInstanceService {

    @Override
    public boolean create(CouponInstanceDTO couponInstanceDto){
        int batchSize=couponInstanceDto.getTotalCount();
        CouponInstance coupInst=couponInstanceDto.clone(CouponInstance.class);
        ArrayList<CouponInstance> entityList=new ArrayList<CouponInstance>();
        for (int i=0;i<batchSize;i++) {
            entityList.add(coupInst);
        }
        saveBatch(entityList,batchSize);
        return false;
    }

}
