package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreTypeRelationDAO;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeRelationDO;
import com.deepexi.channel.domain.store.StoreTypeRelationDTO;
import com.deepexi.channel.service.StoreTypeRelationService;
import com.deepexi.util.pojo.CloneDirection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreTypeRelationServiceImpl implements StoreTypeRelationService {

    @Autowired
    StoreTypeRelationDAO storeTypeRelationDAO;

    @Override
    public Long save(StoreTypeRelationDTO storeTypeRelationDTO) {
        if(storeTypeRelationDTO == null){
            return 0L;
        }
        StoreTypeRelationDO storeTypeRelationDO = storeTypeRelationDTO.clone(StoreTypeRelationDO.class);
        storeTypeRelationDAO.save(storeTypeRelationDO);
        return storeTypeRelationDO.getId();
    }

    @Override
    public StoreTypeRelationDTO getStoreTypeRelationByStoreId(Long pk) {
        StoreTypeRelationDO storeTypeRelationDO = storeTypeRelationDAO.getStoreTypeRelationByStoreId(pk);
        if(storeTypeRelationDO == null){
            return null;
        }
        return storeTypeRelationDO.clone(StoreTypeRelationDTO.class, CloneDirection.OPPOSITE);
    }
}
