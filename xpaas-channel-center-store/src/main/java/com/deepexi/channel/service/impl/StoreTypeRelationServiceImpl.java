package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreTypeRelationDAO;
import com.deepexi.channel.domain.StoreTypeRelationDO;
import com.deepexi.channel.domain.StoreTypeRelationDTO;
import com.deepexi.channel.domain.StoreTypeRelationQuery;
import com.deepexi.channel.service.StoreTypeRelationService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<StoreTypeRelationDTO> findAll(StoreTypeRelationQuery query) {
        List<StoreTypeRelationDO> storeTypeRelationDOS  = storeTypeRelationDAO.findAll(query);
        if(CollectionUtil.isEmpty(storeTypeRelationDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(storeTypeRelationDOS, StoreTypeRelationDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean removeByStoreId(Long storeId) {
        return storeTypeRelationDAO.removeByStoreId(storeId);
    }

    @Override
    public Boolean removeByStoreIds(List<Long> storeIds) {
        if(CollectionUtil.isEmpty(storeIds)){
            return false;
        }
        return storeTypeRelationDAO.removeByStoreIds(storeIds);
    }
}
