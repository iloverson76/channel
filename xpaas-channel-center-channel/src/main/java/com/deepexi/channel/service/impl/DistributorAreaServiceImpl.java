package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorArea;
import com.deepexi.channel.service.DistributorAreaService;
import com.deepexi.channel.mapper.DistributorAreaMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class DistributorAreaServiceImpl implements DistributorAreaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DistributorAreaMapper distributorAreaMapper;

    @Override
    public PageBean<CcDistributorArea> findPage(CcDistributorArea eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributorArea> pages =  distributorAreaMapper.findList(eo);
        return new PageBean<CcDistributorArea>(pages);
    }

    @Override
    public List<CcDistributorArea> findAll(CcDistributorArea eo) {
        List<CcDistributorArea> list = distributorAreaMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributorArea detail(Integer  pk) {
        CcDistributorArea eo = distributorAreaMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributorArea eo) {
        CcDistributorArea old = distributorAreaMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = distributorAreaMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributorArea eo) {
        int result = distributorAreaMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = distributorAreaMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = distributorAreaMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}