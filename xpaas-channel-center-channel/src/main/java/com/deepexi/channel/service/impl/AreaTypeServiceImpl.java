package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcAreaType;
import com.deepexi.channel.service.AreaTypeService;
import com.deepexi.channel.mapper.AreaTypeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class AreaTypeServiceImpl implements AreaTypeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AreaTypeMapper areaTypeMapper;

    @Override
    public PageBean<CcAreaType> findPage(CcAreaType eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcAreaType> pages =  areaTypeMapper.findList(eo);
        return new PageBean<CcAreaType>(pages);
    }

    @Override
    public List<CcAreaType> findAll(CcAreaType eo) {
        List<CcAreaType> list = areaTypeMapper.findList(eo);
        return list;
    }
    @Override
    public CcAreaType detail(Integer  pk) {
        CcAreaType eo = areaTypeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcAreaType eo) {
        CcAreaType old = areaTypeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = areaTypeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcAreaType eo) {
        int result = areaTypeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = areaTypeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = areaTypeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}