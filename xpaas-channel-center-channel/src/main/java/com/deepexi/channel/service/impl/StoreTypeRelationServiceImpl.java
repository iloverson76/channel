package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreTypeRelationDAO;
import com.deepexi.channel.domain.store.StoreTypeRelationDO;
import com.deepexi.channel.service.StoreTypeRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreTypeRelationServiceImpl implements StoreTypeRelationService {

    @Autowired
    StoreTypeRelationDAO storeTypeRelationDAO;

    @Override
    public Long save(StoreTypeRelationDO storeTypeRelationDO) {
        storeTypeRelationDAO.save(storeTypeRelationDO);
        return storeTypeRelationDO.getId();
    }

    @Override
    public StoreTypeRelationDO getStoreTypeRelationByStoreId(Long pk) {
        return storeTypeRelationDAO.getStoreTypeRelationByStoreId(pk);
    }
}
