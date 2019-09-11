package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreChainBusinessService;
import com.deepexi.channel.dao.StoreChainDAO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.store.StoreChainDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.service.ChainService;
import com.deepexi.channel.service.StoreChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreChainBusinessServiceImpl implements StoreChainBusinessService {

    @Autowired
    StoreChainService storeChainService;
    @Autowired
    ChainService chainService;

    @Override
    public Long saveStoreChainRelation(StoreDetailDTO dto) {
        ChainDTO chainDTO = dto.getChainDTO();
        StoreChainDTO storeChainDTO = StoreChainDTO.builder().storeId(dto.getId()).chainId(chainDTO.getId()).build();
        return storeChainService.save(storeChainDTO);
    }

    @Override
    public Long updateStoreChainRelation(StoreDetailDTO dto) {
        //根据门店id删除门店连锁关联
        Boolean result = storeChainService.removeByStoreId(dto.getId());
        //保存最新的门店连锁关联
        return this.saveStoreChainRelation(dto);
    }

    @Override
    public ChainDTO getStoreChainByStoreId(Long storeId) {
        //获取关联信息
        StoreChainDTO storeChainDTO = storeChainService.getStoreChainByStoreId(storeId);
        if(storeChainDTO == null){
            return null;
        }
        //获取连锁
        ChainDTO chainDTO = chainService.detail(storeChainDTO.getChainId());
        return chainDTO;
    }

    /** 
     * @MethodName: deleteStoreChainRelation
     * @Description: 批量删除门店连琐关系
     * @Param: [ids]
     * @Return: java.lang.Boolean
     * @Author: mumu
     * @Date: 2019/9/11
    **/
    @Override
    public Boolean deleteStoreChainRelation(List<Long> ids) {
        return storeChainService.removeByStoreIds(ids);
    }
}
