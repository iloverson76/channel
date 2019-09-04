package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreTypeBusinessService;
import com.deepexi.channel.dao.StoreTypeRelationDAO;
import com.deepexi.channel.domain.store.*;
import com.deepexi.channel.service.StoreTypeRelationService;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.util.CollectionUtil;
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
        StoreTypeRelationQuery storeGradeRelationQuery = StoreTypeRelationQuery.builder().storeTypeIds(ids).build();
        List<StoreTypeRelationDTO> dtos = storeTypeRelationService.findAll(storeGradeRelationQuery);
        if(CollectionUtil.isEmpty(dtos)){
            return false;
        }else{
            return true;
        }
    }
}
