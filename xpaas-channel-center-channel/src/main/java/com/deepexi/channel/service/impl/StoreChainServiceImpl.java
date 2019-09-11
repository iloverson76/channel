package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreChainDAO;
import com.deepexi.channel.domain.store.StoreChainDO;
import com.deepexi.channel.domain.store.StoreChainDTO;
import com.deepexi.channel.service.StoreChainService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public List<StoreChainDTO> getStoreChainByStoreId(Long storeId) {
        List<StoreChainDO> storeChainDOS = storeChainDAO.getByStoreId(storeId);
        if(CollectionUtil.isEmpty(storeChainDOS)){
            return null;
        }
        List<StoreChainDTO> storeChainDTOS = ObjectCloneUtils.convertList(storeChainDOS, StoreChainDTO.class);
        return storeChainDTOS;
    }

    @Override
    public Boolean removeByStoreIds(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return storeChainDAO.removeByStoreIds(ids);
    }

    @Override
    public Boolean saveBatch(List<StoreChainDTO> storeChainDTOS) {
        if(CollectionUtil.isEmpty(storeChainDTOS)){
            return false;
        }
        List<StoreChainDO> storeChainDOS = ObjectCloneUtils.convertList(storeChainDTOS, StoreChainDO.class);
        return storeChainDAO.saveBatch(storeChainDOS);
    }

    /**
     * @MethodName: getStoreChainByChainIds
     * @Description: 更具连琐ids获取关联信息
     * @Param: [ids]
     * @Return: java.util.List<com.deepexi.channel.domain.store.StoreChainDTO>
     * @Author: mumu
     * @Date: 2019/9/11
     **/
    @Override
    public List<StoreChainDTO> getStoreChainByChainIds(List<Long> ids) {
        List<StoreChainDO> list = storeChainDAO.getByChainIds(ids);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        List<StoreChainDTO> result = ObjectCloneUtils.convertList(list, StoreChainDTO.class);
        return result;
    }
}