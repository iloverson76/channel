package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcAreaType;
import com.deepexi.channel.service.CcAreaTypeService;
import com.deepexi.channel.mapper.CcAreaTypeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcAreaTypeServiceImpl implements CcAreaTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcAreaTypeMapper ccAreaTypeMapper;

    @Override
    public PageBean<CcAreaType> findPage(CcAreaType eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcAreaType> pages =  ccAreaTypeMapper.findList(eo);
        return new PageBean<CcAreaType>(pages);
    }

    @Override
    public List<CcAreaType> findAll(CcAreaType eo) {
        List<CcAreaType> list = ccAreaTypeMapper.findList(eo);
        return list;
    }
    @Override
    public CcAreaType detail(Integer  pk) {
        CcAreaType eo = ccAreaTypeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcAreaType eo) {
        CcAreaType old = ccAreaTypeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccAreaTypeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcAreaType eo) {
        int result = ccAreaTypeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccAreaTypeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccAreaTypeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}