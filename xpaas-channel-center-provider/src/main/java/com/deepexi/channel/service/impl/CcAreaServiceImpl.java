package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcArea;
import com.deepexi.channel.service.CcAreaService;
import com.deepexi.channel.mapper.CcAreaMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcAreaServiceImpl implements CcAreaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcAreaMapper ccAreaMapper;

    @Override
    public PageBean<CcArea> findPage(CcArea eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcArea> pages =  ccAreaMapper.findList(eo);
        return new PageBean<CcArea>(pages);
    }

    @Override
    public List<CcArea> findAll(CcArea eo) {
        List<CcArea> list = ccAreaMapper.findList(eo);
        return list;
    }
    @Override
    public CcArea detail(Integer  pk) {
        CcArea eo = ccAreaMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcArea eo) {
        CcArea old = ccAreaMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccAreaMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcArea eo) {
        int result = ccAreaMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccAreaMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccAreaMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}