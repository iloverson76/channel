package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStore;
import com.deepexi.channel.service.StoreService;
import com.deepexi.channel.mapper.StoreMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class StoreServiceImpl implements StoreService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreMapper storeMapper;

    @Override
    public PageBean<CcStore> findPage(CcStore eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStore> pages =  storeMapper.findList(eo);
        return new PageBean<CcStore>(pages);
    }

    @Override
    public List<CcStore> findAll(CcStore eo) {
        List<CcStore> list = storeMapper.findList(eo);
        return list;
    }
    @Override
    public CcStore detail(Integer  pk) {
        CcStore eo = storeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStore eo) {
        CcStore old = storeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = storeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStore eo) {
        int result = storeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = storeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = storeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}