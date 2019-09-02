package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreBusinessService;
import com.deepexi.channel.domain.store.StoreDTO;
import com.deepexi.channel.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreBusinessServiceImpl implements StoreBusinessService {
    @Autowired
    StoreService storeService;

    @Override
    @Transactional
    public Long create(StoreDTO dto) {
        //TODO 校验门店插入是否合法

        //新增门店基本信息
        Long id = storeService.create(dto);

        //新增门店等级关联
        //新增门店类型关联


        //新增门店连锁关联

        //新增门店区域关联

        //新增门店经销商关联

        return id;
    }

    @Override
    public Boolean update(StoreDTO dto) {
        //TODO 校验门店修改是否合法

        //修改门店基本信息
        Boolean result = storeService.update(dto);

        //修改门店等级关联
        //修改门店类型关联


        //修改门店连锁关联
        //修改门店区域关联

        //修改门店经销商关联


        return result;
    }

    @Override
    public StoreDTO detail(Integer pk) {
        StoreDTO result = storeService.detail(pk);

        //获取门店等级关联

        //获取门店类型关联

        //查询门店连锁关联

        //查询门店区域关联

        //查询门店经销商关联


        return result;
    }


}
