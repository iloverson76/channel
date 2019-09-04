package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreTypeBusinessService;
import com.deepexi.channel.dao.StoreTypeRelationDAO;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeRelationDO;
import com.deepexi.channel.domain.store.StoreTypeRelationDTO;
import com.deepexi.channel.service.StoreTypeRelationService;
import com.deepexi.channel.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreTypeBusinessServiceImpl implements StoreTypeBusinessService {

    @Autowired
    StoreTypeService storeTypeService;
    @Autowired
    StoreTypeRelationService storeTypeRelationService;

    @Override
    public Boolean deleteStoreType(List<Long> ids) {

        return storeTypeService.delete(ids);
    }

    @Override
    public Long saveStoreTypeRelation(StoreDetailDTO dto) {
        StoreTypeDTO storeTypeDTO = dto.getStoreTypeDTO();
        StoreTypeRelationDTO storeTypeRelationDTO = StoreTypeRelationDTO.builder().storeTypeId(storeTypeDTO.getId()).storeId(dto.getId()).build();
        return storeTypeRelationService.save(storeTypeRelationDTO);
    }

    @Override
    public StoreTypeDTO getStoreTypeByStoreId(Long pk) {
        StoreTypeRelationDTO storeTypeRelationDTO = storeTypeRelationService.getStoreTypeRelationByStoreId(pk);
        if(storeTypeRelationDTO == null){
            return null;
        }
        StoreTypeDTO storeTypeDTO = storeTypeService.detail(storeTypeRelationDTO.getStoreTypeId());
        return storeTypeDTO;
    }
}
