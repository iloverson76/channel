package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcChainType;
import com.deepexi.channel.service.ChainTypeService;
import com.deepexi.channel.mapper.ChainTypeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class ChainTypeServiceImpl implements ChainTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ChainTypeMapper chainTypeMapper;

    @Override
    public PageBean<CcChainType> findPage(CcChainType eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcChainType> pages =  chainTypeMapper.findList(eo);
        return new PageBean<CcChainType>(pages);
    }

    @Override
    public List<CcChainType> findAll(CcChainType eo) {
        List<CcChainType> list = chainTypeMapper.findList(eo);
        return list;
    }
    @Override
    public CcChainType detail(Integer  pk) {
        CcChainType eo = chainTypeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcChainType eo) {
        CcChainType old = chainTypeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = chainTypeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcChainType eo) {
        int result = chainTypeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = chainTypeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = chainTypeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}