package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.*;
import com.deepexi.channel.service.StoreHistoryBusinessService;
import com.deepexi.channel.service.StoreHistoryService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import com.deepexi.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.StoreType;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/24 17:00
 */
@Service
@Slf4j
public class StoreHistoryBusinessServiceImpl implements StoreHistoryBusinessService {

    @Autowired
    StoreHistoryService storeHistoryService;

    /**
     * @MethodName: storeDetailHistory2storeHistory
     * @Description: storeDetailHistoryDTO转storeHistoryDTO
     * @Param: [storeHistoryDetailDTO]
     * @Return: com.deepexi.channel.domain.StoreHistoryDTO
     * @Author: mumu
     * @Date: 2019/9/24
    **/
    @Override
    public StoreHistoryDTO storeDetailHistory2storeHistory(StoreHistoryDetailDTO storeHistoryDetailDTO) {
        if(storeHistoryDetailDTO == null){
            return null;
        }
        StoreHistoryDTO dto = storeHistoryDetailDTO.clone(StoreHistoryDTO.class);
        if(CollectionUtil.isNotEmpty(storeHistoryDetailDTO.getAreaDTOS())){
            dto.setArea(JsonUtil.bean2JsonString(storeHistoryDetailDTO.getAreaDTOS()));
        }
        if(CollectionUtil.isNotEmpty(storeHistoryDetailDTO.getChainDTOS())){
            dto.setChain(JsonUtil.bean2JsonString(storeHistoryDetailDTO.getChainDTOS()));
        }
        if(CollectionUtil.isNotEmpty(storeHistoryDetailDTO.getStoreDistributorDTOS())){
            dto.setStoreDistributor(JsonUtil.bean2JsonString(storeHistoryDetailDTO.getStoreDistributorDTOS()));
        }
        if(null != storeHistoryDetailDTO.getStoreTypeDTO()){
            dto.setStoreType(JsonUtil.bean2JsonString(storeHistoryDetailDTO.getStoreTypeDTO()));
        }
        if(null != storeHistoryDetailDTO.getStoreGradeDTO()){
            dto.setStoreGrade(JsonUtil.bean2JsonString(storeHistoryDetailDTO.getStoreGradeDTO()));
        }
        return dto;
    }

    @Override
    public StoreHistoryDTO detail(Long pk) {
        StoreHistoryDTO dto = storeHistoryService.detail(pk);
        return dto;
//        StoreHistoryDetailDTO storeHistoryDetailDTO  = this.storeHistory2StoreDetailHistory(dto);
//        return storeHistoryDetailDTO;
    }

    /**
     * @MethodName: storeHistory2StoreDetailHistory
     * @Description: storeHistoryDTO转storeDetailHistoryDTO
     * @Param: [dto]
     * @Return: com.deepexi.channel.domain.StoreHistoryDetailDTO
     * @Author: mumu
     * @Date: 2019/9/24
    **/
    @Override
    public StoreHistoryDetailDTO storeHistory2StoreDetailHistory(StoreHistoryDTO dto) {
        if(null == dto){
            return null;
        }
        StoreHistoryDetailDTO storeHistoryDetailDTO = dto.clone(StoreHistoryDetailDTO.class);
        if(StringUtil.isNotEmpty(dto.getArea())){
            storeHistoryDetailDTO.setAreaDTOS(JsonUtil.json2Bean(dto.getArea(), List.class));
        }
        if(StringUtil.isNotEmpty(dto.getChain())){
            storeHistoryDetailDTO.setChainDTOS(JsonUtil.json2Bean(dto.getChain(), List.class));
        }
        if(StringUtil.isNotEmpty(dto.getStoreDistributor())){
            storeHistoryDetailDTO.setStoreDistributorDTOS(JsonUtil.json2Bean(dto.getStoreDistributor(), List.class));
        }
        if(StringUtil.isNotEmpty(dto.getStoreType())){
            storeHistoryDetailDTO.setStoreTypeDTO(JsonUtil.json2Bean(dto.getStoreType(), StoreTypeDTO.class));
        }
        if(StringUtil.isNotEmpty(dto.getStoreGrade())){
            storeHistoryDetailDTO.setStoreGradeDTO(JsonUtil.json2Bean(dto.getStoreGrade(), StoreGradeDTO.class));
        }
        return storeHistoryDetailDTO;
    }
}