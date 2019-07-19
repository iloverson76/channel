package com.deepexi.promotion.service.impl;

import com.deepexi.promotion.dao.ICouponInstanceDAO;
import com.deepexi.promotion.domain.coupon.CouponInstanceDO;
import com.deepexi.promotion.domain.coupon.CouponInstanceDTO;
import com.deepexi.promotion.service.ICouponInstanceService;
import com.deepexi.util.pojo.AbstractObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author xsj
 * @since 2019-07-16
 */
@Service
public class CouponInstanceServiceImpl implements ICouponInstanceService{

    @Autowired
    ICouponInstanceDAO iCouponInstanceDAO;

    @Override
    public boolean create(CouponInstanceDTO dto){
        int batchSize=dto.getTotalCount();
        CouponInstanceDO inst=dto.clone(CouponInstanceDO.class);
        List<CouponInstanceDO> entityList=new ArrayList<CouponInstanceDO>();
        for (int i=0;i<batchSize;i++) {
            entityList.add(inst);
        }
        return iCouponInstanceDAO.saveBatch(entityList,batchSize);
    }

}
