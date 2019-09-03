package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreGradeBusinessService;
import com.deepexi.channel.dao.StoreGradeRelationDAO;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreGradeDTO;
import com.deepexi.channel.domain.store.StoreGradeRelationDO;
import com.deepexi.channel.domain.store.StoreGradeRelationDTO;
import com.deepexi.channel.service.StoreGradeRelationService;
import com.deepexi.channel.service.StoreGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreGradeBusinessServiceImpl implements StoreGradeBusinessService {

    @Autowired
    StoreGradeService storeGradeService;
    @Autowired
    StoreGradeRelationService storeGradeRelationService;

    @Override
    public Boolean deleteGradeType(List<Long> ids) {
        return storeGradeService.delete(ids);
    }

    @Override
    public Long saveStoreGradeRelation(StoreDetailDTO dto) {
        StoreGradeDTO storeGradeDTO = dto.getStoreGradeDTO();
        StoreGradeRelationDO storeGradeRelationDO = StoreGradeRelationDO.builder().storeGradeId(storeGradeDTO.getId()).storeId(dto.getId()).build();
        storeGradeRelationService.save(storeGradeRelationDO);
        return storeGradeRelationDO.getId();
    }

    @Override
    public StoreGradeDTO getStroeGradeByStoreId(Long pk) {
        StoreGradeRelationDO storeGradeRelationDO = storeGradeRelationService.getStoreGradeRelationByStoreId(pk);
        if(storeGradeRelationDO == null){
            return null;
        }
        StoreGradeDTO storeGradeDTO = storeGradeService.detail(storeGradeRelationDO.getStoreGradeId());
        return storeGradeDTO;
    }
}
