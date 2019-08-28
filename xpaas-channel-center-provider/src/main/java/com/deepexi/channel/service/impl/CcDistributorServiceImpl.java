package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributor;
import com.deepexi.channel.service.CcDistributorService;
import com.deepexi.channel.mapper.CcDistributorMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcDistributorServiceImpl implements CcDistributorService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcDistributorMapper ccDistributorMapper;

    @Override
    public PageBean<CcDistributor> findPage(CcDistributor eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributor> pages =  ccDistributorMapper.findList(eo);
        return new PageBean<CcDistributor>(pages);
    }

    @Override
    public List<CcDistributor> findAll(CcDistributor eo) {
        List<CcDistributor> list = ccDistributorMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributor detail(Integer  pk) {
        CcDistributor eo = ccDistributorMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributor eo) {
        CcDistributor old = ccDistributorMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccDistributorMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributor eo) {
        int result = ccDistributorMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccDistributorMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccDistributorMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}