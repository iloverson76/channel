package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreBusinessService;
import com.deepexi.channel.businness.StoreChainBusinessService;
import com.deepexi.channel.businness.StoreGradeBusinessService;
import com.deepexi.channel.businness.StoreTypeBusinessService;
import com.deepexi.channel.domain.store.*;
import com.deepexi.channel.enums.ResultEnum;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.channel.service.StoreService;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.util.extension.ApplicationException;
import org.checkerframework.checker.units.qual.A;
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
            Long storeChainBusinessId = storeChainBusinessService.saveStoreChainRelation(dto);
        }

        //新增门店区域关联

        //新增门店经销商关联

        return id;
    }

    @Override
    public Boolean update(StoreDetailDTO dto) {
        //修改门店基本信息
        Boolean result = storeService.update(dto);

        //修改门店等级关联
        //修改门店类型关联


        //修改门店连锁关联
        //修改门店区域关联

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

        //查询门店区域关联

        //查询门店经销商关联


        return result;
    }


}
