package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.*;
import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.store.StoreDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.domain.store.StoreGradeDTO;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.service.StoreChainService;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.channel.service.StoreService;
import com.deepexi.channel.service.StoreTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreBusinessServiceImpl implements StoreBusinessService {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreGradeService storeGradeService;
    @Autowired
    StoreTypeService storeTypeService;
    @Autowired
    StoreGradeBusinessService storeGradeBusinessService;
    @Autowired
    StoreTypeBusinessService storeTypeBusinessService;
    @Autowired
    StoreChainBusinessService storeChainBusinessService;
    @Autowired
    StoreChainService storeChainService;
    @Autowired
    StoreAreaBusinessService storeAreaBusinessService;

    @Override
    @Transactional
    public Long create(StoreDetailDTO dto) {
        //新增门店基本信息
        Long id = storeService.create(dto);
        dto.setId(id);
        //新增门店类型关联
        if (dto.getStoreTypeDTO() != null) {
            Long storeTypeRelationId = storeTypeBusinessService.saveStoreTypeRelation(dto);
        }
        //新增门店等级关联
        if (dto.getStoreGradeDTO() != null) {
            Long storeGradeRelationId = storeGradeBusinessService.saveStoreGradeRelation(dto);
        }
        //新增门店连锁关联
        if (dto.getChainDTO() != null) {
            Long storeChainRelationId = storeChainBusinessService.saveStoreChainRelation(dto);
        }
        //新增门店区域关联
        if(dto.getAreaDTO() != null){
            Long storeAreaRelationId = storeAreaBusinessService.saveStoreAreaRelation(dto);
        }

        //新增门店经销商关联

        return id;
    }

    @Override
    @Transactional
    public Boolean update(StoreDetailDTO dto) {
        //修改门店基本信息
        Boolean result = storeService.update(dto);
        //修改门店等级关联
        Long storeGradeUpdateResult = storeGradeBusinessService.updateStoreGradeRelation(dto);
        //修改门店类型关联
        Long storeTypeUpdateResult = storeTypeBusinessService.updateStoreTypeRelation(dto);
        //修改门店连锁关联
        Long storeChainUpdateResult = storeChainBusinessService.updateStoreChainRelation(dto);

        //修改门店区域关联
        Long storeAreaUpdateResult = storeAreaBusinessService.updateStoreAreaRelation(dto);
        //修改门店经销商关联


        return result;
    }

    @Override
    public StoreDetailDTO detail(Long pk) {
        StoreDTO storeDTO = storeService.detail(pk);
        StoreDetailDTO result = storeDTO.clone(StoreDetailDTO.class);
        //获取门店等级关联
        StoreGradeDTO storeGradeDTO = storeGradeBusinessService.getStroeGradeByStoreId(pk);
        result.setStoreGradeDTO(storeGradeDTO);

        //获取门店类型关联
        StoreTypeDTO storeTypeDTO = storeTypeBusinessService.getStoreTypeByStoreId(pk);
        result.setStoreTypeDTO(storeTypeDTO);

        //查询门店连锁关联
        ChainDTO chainDTO = storeChainBusinessService.getStoreChainByStoreId(pk);
        result.setChainDTO(chainDTO);
        //查询门店区域关联
        AreaDTO areaDTO = storeAreaBusinessService.getStoreAreaByStoreId(pk);
        result.setAreaDTO(areaDTO);

        //查询门店经销商关联


        return result;
    }


}
