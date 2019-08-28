package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcChain;
import com.deepexi.channel.service.CcChainService;
import com.deepexi.channel.mapper.CcChainMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcChainServiceImpl implements CcChainService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcChainMapper ccChainMapper;

    @Override
    public PageBean<CcChain> findPage(CcChain eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcChain> pages =  ccChainMapper.findList(eo);
        return new PageBean<CcChain>(pages);
    }

    @Override
    public List<CcChain> findAll(CcChain eo) {
        List<CcChain> list = ccChainMapper.findList(eo);
        return list;
    }
    @Override
    public CcChain detail(Integer  pk) {
        CcChain eo = ccChainMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcChain eo) {
        CcChain old = ccChainMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccChainMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcChain eo) {
        int result = ccChainMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccChainMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccChainMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}