package com.deepexi.channel.service.impl;

import com.deepexi.channel.dao.StoreTypeDAO;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.channel.domain.store.StoreTypeVO;
import com.deepexi.util.CollectionUtil;
import com.deepexi.util.pojo.CloneDirection;
import com.deepexi.util.pojo.ObjectCloneUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreType;
import com.deepexi.channel.service.StoreTypeService;
import com.deepexi.channel.mapper.StoreTypeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class StoreTypeServiceImpl implements StoreTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreTypeDAO storeTypeDAO;

    @Override
    public List<StoreTypeDTO> findPage(StoreTypeQuery query) {
        if(query.getPage() != null && query.getPage() != -1){
            PageHelper.startPage(query.getPage(), query.getSize());
        }
        List<StoreTypeDO> storeTypeDOS =  storeTypeDAO.findList(query);
        if(CollectionUtil.isEmpty(storeTypeDOS)){
            return null;
        }
        return ObjectCloneUtils.convertList(storeTypeDOS, StoreTypeDTO.class, CloneDirection.OPPOSITE);
    }

    @Override
    public StoreTypeDTO detail(Integer  pk) {
        StoreTypeDO storeTypeDO = storeTypeDAO.getById(pk);
        if(storeTypeDO == null){
            return null;
        }
        return storeTypeDO.clone(StoreTypeDTO.class);
    }

    @Override
    public Boolean update(StoreTypeDTO dto) {
        if(dto == null){
            return false;
        }
        //TODO 判断编码是否重复
        StoreTypeDO storeTypeDO = dto.clone(StoreTypeDO.class);
        return storeTypeDAO.updateById(storeTypeDO);
    }

    @Override
    public Boolean create(StoreTypeDTO dto) {
        if(dto == null){
            return false;
        }
        //TODO 判断编码是否重复
        StoreTypeDO storeTypeDO = dto.clone(StoreTypeDO.class);
        return storeTypeDAO.save(storeTypeDO);
    }

    @Override
    public Boolean delete(List<Long> ids) {
        if(CollectionUtil.isEmpty(ids)){
            return false;
        }
        return storeTypeDAO.removeByIds(ids);
    }

}