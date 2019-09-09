package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreDistributorRelationDAO;
import com.deepexi.channel.domain.store.StoreDistributorRelationDO;
import com.deepexi.channel.domain.store.StoreDistributorRelationDTO;
import com.deepexi.channel.domain.store.StoreDistributorRelationQuery;
import com.deepexi.channel.mapper.StoreDistributorRelationMapper;
import com.deepexi.channel.service.StoreDistributorRelationService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/7 14:20
 */
@Service
public class StoreDistributorRelationServiceImpl implements StoreDistributorRelationService {

    @Autowired
    StoreDistributorRelationDAO storeDistributorRelationDAO;

    @Override
    public List<StoreDistributorRelationDTO> findList(StoreDistributorRelationQuery query) {
        List<StoreDistributorRelationDO> list = storeDistributorRelationDAO.findList(query);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        List<StoreDistributorRelationDTO> dtos = ObjectCloneUtils.convertList(list, StoreDistributorRelationDTO.class);
        return dtos;
    }

    @Override
    public Boolean saveBatch(List<StoreDistributorRelationDTO> relationDTOS) {
        if(CollectionUtil.isEmpty(relationDTOS)){
            return false;
        }
        List<StoreDistributorRelationDO> storeDistributorRelationDOS = ObjectCloneUtils.convertList(relationDTOS, StoreDistributorRelationDO.class);
        return storeDistributorRelationDAO.saveBatch(storeDistributorRelationDOS);
    }

    @Override
    public Boolean deleteByStoreId(long storeId) {
        return storeDistributorRelationDAO.deleteByStoreId(storeId);
    }
}