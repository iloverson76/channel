package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorBank;
import com.deepexi.channel.service.DistributorBankService;
import com.deepexi.channel.mapper.DistributorBankMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class DistributorBankServiceImpl implements DistributorBankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DistributorBankMapper distributorBankMapper;

    @Override
    public PageBean<CcDistributorBank> findPage(CcDistributorBank eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributorBank> pages =  distributorBankMapper.findList(eo);
        return new PageBean<CcDistributorBank>(pages);
    }

    @Override
    public List<CcDistributorBank> findAll(CcDistributorBank eo) {
        List<CcDistributorBank> list = distributorBankMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributorBank detail(Integer  pk) {
        CcDistributorBank eo = distributorBankMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributorBank eo) {
        CcDistributorBank old = distributorBankMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = distributorBankMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributorBank eo) {
        int result = distributorBankMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = distributorBankMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = distributorBankMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}