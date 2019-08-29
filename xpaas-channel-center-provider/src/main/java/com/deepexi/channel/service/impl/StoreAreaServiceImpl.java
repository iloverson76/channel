package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreArea;
import com.deepexi.channel.service.StoreAreaService;
import com.deepexi.channel.mapper.StoreAreaMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class StoreAreaServiceImpl implements StoreAreaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreAreaMapper storeAreaMapper;

    @Override
    public PageBean<CcStoreArea> findPage(CcStoreArea eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreArea> pages =  storeAreaMapper.findList(eo);
        return new PageBean<CcStoreArea>(pages);
    }

    @Override
    public List<CcStoreArea> findAll(CcStoreArea eo) {
        List<CcStoreArea> list = storeAreaMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreArea detail(Integer  pk) {
        CcStoreArea eo = storeAreaMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreArea eo) {
        CcStoreArea old = storeAreaMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = storeAreaMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreArea eo) {
        int result = storeAreaMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = storeAreaMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = storeAreaMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}