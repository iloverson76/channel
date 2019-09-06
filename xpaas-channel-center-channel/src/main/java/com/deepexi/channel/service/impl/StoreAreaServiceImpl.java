package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreAreaDAO;
import com.deepexi.channel.domain.store.StoreAreaDO;
import com.deepexi.channel.domain.store.StoreAreaDTO;
import com.deepexi.channel.service.StoreAreaService;
import com.deepexi.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
@Service
public class StoreAreaServiceImpl implements StoreAreaService {

    @Autowired
    StoreAreaDAO storeAreaDAO;

    @Override
    public Long save(StoreAreaDTO storeAreaDTO) {
        if(storeAreaDTO == null){
            return 0L;
        }
        StoreAreaDO storeAreaDO = storeAreaDTO.clone(StoreAreaDO.class);
        storeAreaDAO.save(storeAreaDO);
        return storeAreaDO.getId();
    }

    @Override
    public Boolean removeByStoreId(Long storeId) {
        return storeAreaDAO.removeByStoreId(storeId);
    }

    @Override
    public StoreAreaDTO getStoreAreaByStoreId(Long storeId) {
        List<StoreAreaDO> storeAreaDOS = storeAreaDAO.getByStoreId(storeId);
        if(CollectionUtil.isEmpty(storeAreaDOS)){
            return null;
        }
        StoreAreaDO storeAreaDO = storeAreaDOS.get(0);
        return storeAreaDO.clone(StoreAreaDTO.class);
    }
}