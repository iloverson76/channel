package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreTypeBusinessService;
import com.deepexi.channel.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreTypeBusinessServiceImpl implements StoreTypeBusinessService {

    @Autowired
    StoreTypeService storeTypeService;

    @Override
    public Boolean deleteStoreType(List<Long> ids) {
        //TODO 校验是否有门店关联该门店类型

        return storeTypeService.delete(ids);
    }
}
