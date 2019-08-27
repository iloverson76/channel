package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.IStoreTypeDAO;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.service.IStoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StoreTypeServiceImpl implements IStoreTypeService {

    @Autowired
    IStoreTypeDAO iStoreTypeDAO;

    @Override
    public StoreTypeDTO getStoreType(Long id) {
        if (id == null || id == 0L) {
            return null;
        }
        StoreTypeDO storeTypeDO = iStoreTypeDAO.getById(id);
        if (null == storeTypeDO) {
            return null;
        }
        return storeTypeDO.clone(StoreTypeDTO.class);
    }

    @Override
    public List<StoreTypeDTO> listStoreType(StoreTypeQuery query) {
//        List<StoreTypeDO> result =  iChainTypeDAO.listChainTypePage(query);
//        return ObjectCloneUtils.convertList(result, StoreTypeDTO.class, CloneDirection.OPPOSITE);
        return null;
    }

    @Override
    public Boolean insert(StoreTypeDTO dto) {
        return null;
    }

    @Override
    public Boolean update(StoreTypeDTO dto) {
        return null;
    }

    @Override
    public Boolean delete(List<Long> ids) {
        return null;
    }
}
