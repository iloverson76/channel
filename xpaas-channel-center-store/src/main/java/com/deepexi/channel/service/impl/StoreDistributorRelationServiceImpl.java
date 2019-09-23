package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreDistributorRelationDAO;
import com.deepexi.channel.domain.StoreDistributorRelationDO;
import com.deepexi.channel.domain.StoreDistributorRelationDTO;
import com.deepexi.channel.domain.StoreDistributorRelationQuery;
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
    public StoreDistributorRelationDTO detail(Long id) {
        StoreDistributorRelationDO storeDistributorRelationDO = storeDistributorRelationDAO.getById(id);
        if(storeDistributorRelationDO == null){
            return null;
        }
        return storeDistributorRelationDO.clone(StoreDistributorRelationDTO.class);
    }

    @Override
    public Boolean save(StoreDistributorRelationDTO dto) {
        if(dto == null){
            return false;
        }
        return storeDistributorRelationDAO.save(dto.clone(StoreDistributorRelationDO.class));
    }

    @Override
    public Boolean update(StoreDistributorRelationDTO dto) {
        if(dto == null){
            return false;
        }
        return storeDistributorRelationDAO.updateById(dto.clone(StoreDistributorRelationDO.class));
    }

    @Override
    public Boolean delete(Long id) {
        return storeDistributorRelationDAO.removeById(id);
    }

    @Override
    public Boolean delete(List<Long> ids) {
        return storeDistributorRelationDAO.removeByIds(ids);
    }

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

    @Override
    public Boolean deleteByStoreIds(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return storeDistributorRelationDAO.deleteByStoreIds(ids);
    }
}