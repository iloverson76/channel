package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreChainDAO;
import com.deepexi.channel.domain.store.StoreChainDO;
import com.deepexi.channel.domain.store.StoreChainDTO;
import com.deepexi.channel.service.StoreChainService;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 11:20
 */
@Service
public class StoreChainServiceImpl implements StoreChainService {

    @Autowired
    StoreChainDAO storeChainDAO;

    @Override
    public Long save(StoreChainDTO storeChainDTO) {
        if(storeChainDTO == null){
            return 0L;
        }
        StoreChainDO storeChainDO = storeChainDTO.clone(StoreChainDO.class);
        storeChainDAO.save(storeChainDO);
        return storeChainDO.getId();
    }

    @Override
    public Boolean removeByStoreId(Long storeId) {
        return storeChainDAO.removeByStoreId(storeId);
    }

    @Override
    public StoreChainDTO getStoreChainByStoreId(Long storeId) {
        List<StoreChainDO> storeChainDOS = storeChainDAO.getByStoreId(storeId);
        if(CollectionUtil.isEmpty(storeChainDOS)){
            return null;
        }
        StoreChainDO storeChainDO = storeChainDOS.get(0);
        return storeChainDO.clone(StoreChainDTO.class);
    }
}