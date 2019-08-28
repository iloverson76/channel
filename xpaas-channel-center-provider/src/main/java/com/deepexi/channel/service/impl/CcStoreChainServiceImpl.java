package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreChain;
import com.deepexi.channel.service.CcStoreChainService;
import com.deepexi.channel.mapper.CcStoreChainMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcStoreChainServiceImpl implements CcStoreChainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcStoreChainMapper ccStoreChainMapper;

    @Override
    public PageBean<CcStoreChain> findPage(CcStoreChain eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreChain> pages =  ccStoreChainMapper.findList(eo);
        return new PageBean<CcStoreChain>(pages);
    }

    @Override
    public List<CcStoreChain> findAll(CcStoreChain eo) {
        List<CcStoreChain> list = ccStoreChainMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreChain detail(Integer  pk) {
        CcStoreChain eo = ccStoreChainMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreChain eo) {
        CcStoreChain old = ccStoreChainMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccStoreChainMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreChain eo) {
        int result = ccStoreChainMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccStoreChainMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccStoreChainMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}