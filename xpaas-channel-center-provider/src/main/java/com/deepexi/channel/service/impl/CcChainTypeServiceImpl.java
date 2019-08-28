package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcChainType;
import com.deepexi.channel.service.CcChainTypeService;
import com.deepexi.channel.mapper.CcChainTypeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcChainTypeServiceImpl implements CcChainTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcChainTypeMapper ccChainTypeMapper;

    @Override
    public PageBean<CcChainType> findPage(CcChainType eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcChainType> pages =  ccChainTypeMapper.findList(eo);
        return new PageBean<CcChainType>(pages);
    }

    @Override
    public List<CcChainType> findAll(CcChainType eo) {
        List<CcChainType> list = ccChainTypeMapper.findList(eo);
        return list;
    }
    @Override
    public CcChainType detail(Integer  pk) {
        CcChainType eo = ccChainTypeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcChainType eo) {
        CcChainType old = ccChainTypeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccChainTypeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcChainType eo) {
        int result = ccChainTypeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccChainTypeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccChainTypeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}