package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreGradeDAO;
import com.deepexi.channel.dao.StoreGradeRelationDAO;
import com.deepexi.channel.domain.store.StoreGradeRelationDO;
import com.deepexi.channel.service.StoreGradeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreGradeRelationServiceImpl implements StoreGradeRelationService {

    @Autowired
    StoreGradeRelationDAO storeGradeRelationDAO;

    @Override
    public Long save(StoreGradeRelationDO storeGradeRelationDO) {
        storeGradeRelationDAO.save(storeGradeRelationDO);
        return storeGradeRelationDO.getId();
    }

    @Override
    public StoreGradeRelationDO getStoreGradeRelationByStoreId(Long pk) {
        return storeGradeRelationDAO.getStoreGradeRelationByStoreId(pk);
    }
}
