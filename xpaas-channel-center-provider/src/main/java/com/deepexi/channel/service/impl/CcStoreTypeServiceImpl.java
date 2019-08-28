package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreType;
import com.deepexi.channel.service.CcStoreTypeService;
import com.deepexi.channel.mapper.CcStoreTypeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcStoreTypeServiceImpl implements CcStoreTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcStoreTypeMapper ccStoreTypeMapper;

    @Override
    public PageBean<CcStoreType> findPage(CcStoreType eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreType> pages =  ccStoreTypeMapper.findList(eo);
        return new PageBean<CcStoreType>(pages);
    }

    @Override
    public List<CcStoreType> findAll(CcStoreType eo) {
        List<CcStoreType> list = ccStoreTypeMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreType detail(Integer  pk) {
        CcStoreType eo = ccStoreTypeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreType eo) {
        CcStoreType old = ccStoreTypeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccStoreTypeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreType eo) {
        int result = ccStoreTypeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccStoreTypeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccStoreTypeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}