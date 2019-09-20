package com.deepexi.channel.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deepexi.channel.dao.StoreGradeRelationDAO;
import com.deepexi.channel.domain.store.StoreGradeRelationDO;
import com.deepexi.channel.domain.store.StoreGradeRelationDTO;
import com.deepexi.channel.domain.store.StoreGradeRelationQuery;
import com.deepexi.channel.service.StoreGradeRelationService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreGradeRelationServiceImpl implements StoreGradeRelationService {

    @Autowired
    StoreGradeRelationDAO storeGradeRelationDAO;

    @Override
    public List<StoreGradeRelationDTO> findAll(StoreGradeRelationQuery query) {
        List<StoreGradeRelationDO> storeGradeRelationDOS  = storeGradeRelationDAO.findAll(query);
        if(CollectionUtil.isEmpty(storeGradeRelationDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(storeGradeRelationDOS, StoreGradeRelationDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public Long save(StoreGradeRelationDTO storeGradeRelationDTO) {
        if(storeGradeRelationDTO == null){
            return 0L;
        }
        StoreGradeRelationDO storeGradeRelationDO = storeGradeRelationDTO.clone(StoreGradeRelationDO.class);
        storeGradeRelationDAO.save(storeGradeRelationDO);
        return storeGradeRelationDO.getId();
    }

    @Override
    public StoreGradeRelationDTO detail(Long id) {
        StoreGradeRelationDO storeGradeRelationDO = storeGradeRelationDAO.getById(id);
        if(storeGradeRelationDO == null){
            return null;
        }
        return storeGradeRelationDO.clone(StoreGradeRelationDTO.class);
    }

    @Override
    public boolean delete(Long id) {
        return storeGradeRelationDAO.removeById(id);
    }

    @Override
    public boolean delete(List<Long> ids) {
        return storeGradeRelationDAO.removeByIds(ids);
    }

    @Override
    public StoreGradeRelationDTO getStoreGradeRelationByStoreId(Long pk) {
        StoreGradeRelationDO storeGradeRelationDO = storeGradeRelationDAO.getStoreGradeRelationByStoreId(pk);
        if(storeGradeRelationDO == null){
            return null;
        }
        return storeGradeRelationDO.clone(StoreGradeRelationDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public Boolean removeByStoreId(Long storeId) {
        return storeGradeRelationDAO.removeByStoreId(storeId);
    }

    @Override
    public Boolean removeByStoreIds(List<Long> storeIds) {
        if(CollectionUtil.isEmpty(storeIds)) {
            return false;
        }
        return storeGradeRelationDAO.removeByStoreIds(storeIds);
    }
}
