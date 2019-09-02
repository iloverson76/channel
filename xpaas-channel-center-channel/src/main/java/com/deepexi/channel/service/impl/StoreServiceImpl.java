package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreDAO;
import com.deepexi.channel.domain.store.StoreDO;
import com.deepexi.channel.domain.store.StoreDTO;
import com.deepexi.channel.domain.store.StoreQuery;
import com.deepexi.channel.service.StoreService;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreDAO storeDAO;

    @Override
    public List<StoreDTO> findPage(StoreQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<StoreDO> storeDOS =  storeDAO.findList(query);
        if(CollectionUtil.isEmpty(storeDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(storeDOS, StoreDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public StoreDTO detail(Integer  pk) {
        StoreDO storeDO = storeDAO.getById(pk);
        if(storeDO == null ){
            return null;
        }
        return storeDO.clone(StoreDTO.class);
    }

    @Override
    public Boolean update(StoreDTO dto) {
        if(dto == null){
            return false;
        }
        //TODO 判断编码是否重复
        StoreDO storeDO = dto.clone(StoreDO.class);
        return storeDAO.updateById(storeDO);
    }
    @Override
    public Long create(StoreDTO dto) {
        if(dto == null){
            return 0L;
        }
        //TODO 判断编码是否重复
        StoreDO storeDO = dto.clone(StoreDO.class);
        storeDAO.save(storeDO);
        return storeDO.getId();
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return storeDAO.removeByIds(ids);
    }

}