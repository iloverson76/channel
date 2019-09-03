package com.deepexi.channel.businness.impl;

import com.deepexi.channel.businness.StoreChainBusinessService;
import com.deepexi.channel.dao.StoreChainDAO;
import com.deepexi.channel.domain.store.StoreDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreChainBusinessServiceImpl implements StoreChainBusinessService {

    @Autowired
    StoreChainDAO storeChainDAO;


    @Override
    public Long saveStoreChainRelation(StoreDetailDTO dto) {
        return null;
    }
}
