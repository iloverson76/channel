package com.deepexi.channel.service.impl;

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
    StoreDistributorRelationMapper storeDistributorRelationMapper;

    @Override
    public List<StoreDistributorRelationDTO> findList(StoreDistributorRelationQuery query) {
        List<StoreDistributorRelationDO> list = storeDistributorRelationMapper.findList(query);
        if(CollectionUtil.isEmpty(list)){
            return null;
        }
        List<StoreDistributorRelationDTO> dtos = ObjectCloneUtils.convertList(list, StoreDistributorRelationDTO.class);
        return dtos;
    }
}