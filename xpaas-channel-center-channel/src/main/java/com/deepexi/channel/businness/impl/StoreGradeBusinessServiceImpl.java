package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreGradeBusinessService;
import com.deepexi.channel.dao.StoreGradeRelationDAO;
import com.deepexi.channel.domain.store.*;
import com.deepexi.channel.service.StoreGradeRelationService;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.channel.service.StoreService;
import com.deepexi.util.CollectionUtil;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreGradeBusinessServiceImpl implements StoreGradeBusinessService {

    @Autowired
    StoreGradeService storeGradeService;
    @Autowired
    StoreGradeRelationService storeGradeRelationService;
    @Autowired
    StoreService storeService;

    @Override
    public Boolean deleteGradeType(List<Long> ids) {
        return storeGradeService.delete(ids);
    }

    @Override
    public Long saveStoreGradeRelation(StoreDetailDTO dto) {
        StoreGradeDTO storeGradeDTO = dto.getStoreGradeDTO();
        StoreGradeRelationDTO storeGradeRelationDTO = StoreGradeRelationDTO.builder().storeGradeId(storeGradeDTO.getId()).storeId(dto.getId()).build();
        return storeGradeRelationService.save(storeGradeRelationDTO);

    }

    @Override
    public StoreGradeDTO getStroeGradeByStoreId(Long pk) {
        StoreGradeRelationDTO storeGradeRelationDTO = storeGradeRelationService.getStoreGradeRelationByStoreId(pk);
        if(storeGradeRelationDTO == null){
            return null;
        }
        StoreGradeDTO storeGradeDTO = storeGradeService.detail(storeGradeRelationDTO.getStoreGradeId());
        return storeGradeDTO;
    }

    /**
     * @MethodName: haveStoreRelation
     * @Description: 是否关联门店，ture 关联，不能删除   false 没关联，可以删除
     * @Param: [ids]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/4
    **/
    @Override
    public boolean haveStoreRelation(List<Long> ids) {
        StoreGradeRelationQuery storeGradeRelationQuery = StoreGradeRelationQuery.builder().storeGradeIds(ids).build();
        List<StoreGradeRelationDTO> dtos = storeGradeRelationService.findAll(storeGradeRelationQuery);
        if(CollectionUtil.isEmpty(dtos)){
            return false;
        }else{
            return true;
        }
    }
}
