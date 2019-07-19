package com.deepexi.promotion.service.impl;

import com.deepexi.promotion.domain.coupon.CouponInstanceDO;
import com.deepexi.promotion.domain.coupon.CouponInstanceDTO;
import com.deepexi.promotion.mapper.CouponInstanceMapper;
import com.deepexi.promotion.service.ICouponInstanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * <p>
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Service
public class CouponInstanceServiceImpl extends ServiceImpl<CouponInstanceMapper, CouponInstanceDO> implements ICouponInstanceService {

    @Override
    public boolean create(CouponInstanceDTO couponInstanceDto){
        int batchSize=couponInstanceDto.getTotalCount();
        CouponInstanceDO coupInst=couponInstanceDto.clone(CouponInstanceDO.class);
        ArrayList<CouponInstanceDO> entityList=new ArrayList<CouponInstanceDO>();
        for (int i=0;i<batchSize;i++) {
            entityList.add(coupInst);
        }
        saveBatch(entityList,batchSize);
        return false;
    }

}
