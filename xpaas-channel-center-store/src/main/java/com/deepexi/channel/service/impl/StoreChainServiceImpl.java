package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreChainDAO;
import com.deepexi.channel.domain.StoreChainDO;
import com.deepexi.channel.domain.StoreChainDTO;
import com.deepexi.channel.domain.StoreChainQuery;
import com.deepexi.channel.service.StoreChainService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 11:20
 */
@Slf4j
@Service
public class StoreChainServiceImpl implements StoreChainService {

    @Autowired
    StoreChainDAO storeChainDAO;

    @Override
    public List<StoreChainDTO> findList(StoreChainQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<StoreChainDO> list = storeChainDAO.findList(query);
        if(CollectionUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return ObjectCloneUtils.convertList(list, StoreChainDTO.class);
    }

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

    @Override
    public Boolean removeByChainIds(List<Long> ids) {
        log.info("根据连琐id列表删除门店连琐关联，ids={}",ids);
        return storeChainDAO.removeByChainIds(ids);
    }

}