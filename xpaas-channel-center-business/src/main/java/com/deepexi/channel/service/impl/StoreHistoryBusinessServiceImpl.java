package com.deepexi.channel.service.impl;

import com.deepexi.channel.domain.StoreHistoryDTO;
import com.deepexi.channel.domain.StoreHistoryDetailDTO;
import com.deepexi.channel.service.StoreHistoryBusinessService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/24 17:00
 */
@Service
@Slf4j
public class StoreHistoryBusinessServiceImpl implements StoreHistoryBusinessService {

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
}