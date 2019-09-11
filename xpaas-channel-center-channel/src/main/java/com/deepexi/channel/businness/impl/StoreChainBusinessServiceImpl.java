package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.ChainBusinessService;
import com.deepexi.channel.businness.StoreChainBusinessService;
import com.deepexi.channel.dao.StoreChainDAO;
import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainDetailDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.store.StoreChainDTO;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import com.deepexi.channel.service.ChainService;
import com.deepexi.channel.service.StoreChainService;
import com.deepexi.util.CollectionUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreChainBusinessServiceImpl implements StoreChainBusinessService {

    @Autowired
    StoreChainService storeChainService;
    @Autowired
    ChainService chainService;
    @Autowired
    ChainBusinessService chainBusinessService;

    @Override
    public Boolean saveStoreChainRelation(StoreDetailDTO dto) {
        List<ChainDetailDTO> chainDTOS = dto.getChainDTOS();
        List<StoreChainDTO> storeChainDTOS = new LinkedList<>();
        chainDTOS.forEach(c->{
            StoreChainDTO storeChainDTO = StoreChainDTO.builder().storeId(dto.getId()).chainId(c.getId()).build();
            storeChainDTOS.add(storeChainDTO);
        });
        return storeChainService.saveBatch(storeChainDTOS);
    }

    @Override
    public Boolean updateStoreChainRelation(StoreDetailDTO dto) {
        //根据门店id删除门店连锁关联
        Boolean result = storeChainService.removeByStoreId(dto.getId());
        //保存最新的门店连锁关联
        return this.saveStoreChainRelation(dto);
    }

    @Override
    public List<ChainDetailDTO> getStoreChainByStoreId(Long storeId) {
        //获取关联信息
        List<StoreChainDTO> storeChainDTOS = storeChainService.getStoreChainByStoreId(storeId);
        if(CollectionUtil.isEmpty(storeChainDTOS)){
            return null;
        }
        //更具连琐id列表获取连锁
        List<Long> ids = storeChainDTOS.stream().map(StoreChainDTO::getChainId).collect(Collectors.toList());
        ChainQuery query = ChainQuery.builder().ids(ids).build();
        List<ChainDetailDTO> chainDTOS = chainBusinessService.findPage(query);
        return chainDTOS;
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
