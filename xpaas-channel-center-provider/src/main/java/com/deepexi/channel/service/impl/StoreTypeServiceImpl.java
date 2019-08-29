package com.deepexi.channel.service.impl;

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
    private StoreTypeMapper storeTypeMapper;

    @Override
    public PageBean<CcStoreType> findPage(CcStoreType eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreType> pages =  storeTypeMapper.findList(eo);
        return new PageBean<CcStoreType>(pages);
    }

    @Override
    public List<CcStoreType> findAll(CcStoreType eo) {
        List<CcStoreType> list = storeTypeMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreType detail(Integer  pk) {
        CcStoreType eo = storeTypeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreType eo) {
        CcStoreType old = storeTypeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = storeTypeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreType eo) {
        int result = storeTypeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = storeTypeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = storeTypeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}