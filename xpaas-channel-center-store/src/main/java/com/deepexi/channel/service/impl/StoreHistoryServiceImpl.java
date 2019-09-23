package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreHistoryDAO;
import com.deepexi.channel.domain.StoreHistoryDO;
import com.deepexi.channel.domain.StoreHistoryDTO;
import com.deepexi.channel.domain.StoreHistoryQuery;
import com.deepexi.channel.service.StoreHistoryService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/7 11:06
 */
@Service
public class StoreHistoryServiceImpl implements StoreHistoryService {

    @Autowired
    StoreHistoryDAO storeHistoryDAO;

    @Override
    public List<StoreHistoryDTO> findPage(StoreHistoryQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<StoreHistoryDO> storeHistoryDOS = storeHistoryDAO.findList(query);
        if(CollectionUtil.isEmpty(storeHistoryDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(storeHistoryDOS, StoreHistoryDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public StoreHistoryDTO detail(Long pk) {
        StoreHistoryDO storeHistoryDO = storeHistoryDAO.getById(pk);
        if(storeHistoryDO == null ){
            return null;
        }
        return storeHistoryDO.clone(StoreHistoryDTO.class);
    }

    @Override
    public Boolean update(StoreHistoryDTO dto) {
        if(dto == null){
            return false;
        }
        StoreHistoryDO storeHistoryDO = dto.clone(StoreHistoryDO.class);
        return storeHistoryDAO.updateById(storeHistoryDO);
    }

    @Override
    public Long create(StoreHistoryDTO dto) {
        if(dto == null){
            return 0L;
        }
        StoreHistoryDO storeHistoryDO = dto.clone(StoreHistoryDO.class);
        storeHistoryDAO.save(storeHistoryDO);
        return storeHistoryDO.getId();
    }

    @Override
    public Boolean delete(Long id) {
        return storeHistoryDAO.removeById(id);
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return storeHistoryDAO.removeByIds(ids);
    }

    @Override
    public Integer getStoreHistoryCountByStoreId(Long storeId) {
        return storeHistoryDAO.getStoreHistoryCountByStoreId(storeId);
    }

}