package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.StoreGradeDO;
import com.deepexi.channel.domain.StoreGradeQuery;

import java.util.List;

public interface StoreGradeDAO extends IService<StoreGradeDO> {
    List<StoreGradeDO> findList(StoreGradeQuery query);
//    Page<StoreTypeDO> listStoreTypePage(StoreGradeQuery query);
}
