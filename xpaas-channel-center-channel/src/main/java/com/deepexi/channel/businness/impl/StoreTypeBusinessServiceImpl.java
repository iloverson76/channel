package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreTypeBusinessService;
import com.deepexi.channel.dao.StoreTypeRelationDAO;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeRelationDO;
import com.deepexi.channel.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreTypeBusinessServiceImpl implements StoreTypeBusinessService {

    @Autowired
    StoreTypeService storeTypeService;
    @Autowired
    StoreTypeRelationDAO storeTypeRelationDAO;

    @Override
    public Boolean deleteStoreType(List<Long> ids) {

        return storeTypeService.delete(ids);
    }

    @Override
    public Long saveStoreTypeRelation(StoreDetailDTO dto) {
        StoreTypeDTO storeTypeDTO = dto.getStoreTypeDTO();
        StoreTypeRelationDO storeTypeRelationDO = StoreTypeRelationDO.builder().storeTypeId(storeTypeDTO.getId()).storeId(dto.getId()).build();
        storeTypeRelationDAO.save(storeTypeRelationDO);
        return storeTypeRelationDO.getId();
    }
}
