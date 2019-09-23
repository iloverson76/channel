package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.StoreDetailDTO;
import com.deepexi.channel.domain.StoreGradeDTO;
import com.deepexi.channel.domain.StoreGradeRelationDTO;
import com.deepexi.channel.domain.StoreGradeRelationQuery;
import com.deepexi.channel.service.StoreGradeBusinessService;
import com.deepexi.channel.service.StoreGradeRelationService;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.channel.service.StoreService;
import com.deepexi.util.CollectionUtil;
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

    @Override
    public Long updateStoreGradeRelation(StoreDetailDTO dto) {
        //删除门店关系
        Boolean result = storeGradeRelationService.removeByStoreId(dto.getId());
        //新增门店关系
       return this.saveStoreGradeRelation(dto);
    }

    /**
     * @MethodName: deleteStoreGradeRelation
     * @Description: 根据storeId批量删除门店等级关联
     * @Param: [storeIds]
     * @Return: java.lang.Boolean
     * @Author: mumu
     * @Date: 2019/9/11
    **/
    @Override
    public Boolean deleteStoreGradeRelation(List<Long> storeIds) {
        return storeGradeRelationService.removeByStoreIds(storeIds);
    }
}
