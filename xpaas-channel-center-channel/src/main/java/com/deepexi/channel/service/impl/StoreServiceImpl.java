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
    public StoreDTO detail(Long pk) {
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
        StoreDO storeDO = dto.clone(StoreDO.class);
        return storeDAO.updateById(storeDO);
    }
    @Override
    public Long create(StoreDTO dto) {
        if(dto == null){
            return 0L;
        }
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

    @Override
    public boolean isCodeUnique(StoreDTO dto) {
        StoreQuery storeQuery = StoreQuery.builder().storeAccuracyCode(dto.getStoreCode()).build();
        List<StoreDO> list = storeDAO.findList(storeQuery);
        if(CollectionUtil.isNotEmpty(list)){
            //不为空，还有可能是更新时自身的编码 ，当然，前端不能调皮新增时传id过来
            if(list.size()==1){
                StoreDO storeDO = list.get(0);
                //该code是本身，不属于重复
                if(storeDO.getId().equals(dto.getId())){
                    return true;
                }
            }
            return false;
        }
        return true;
    }

}