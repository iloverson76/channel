package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreGradeBusinessService;
import com.deepexi.channel.service.StoreGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreGradeBusinessServiceImpl implements StoreGradeBusinessService {

    @Autowired
    StoreGradeService storeGradeService;

    @Override
    public Boolean deleteGradeType(List<Long> ids) {
        //TODO 校验是否有门店关联该门店等级

        return storeGradeService.delete(ids);
    }
}
