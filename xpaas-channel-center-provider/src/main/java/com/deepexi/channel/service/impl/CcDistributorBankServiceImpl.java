package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorBank;
import com.deepexi.channel.service.CcDistributorBankService;
import com.deepexi.channel.mapper.CcDistributorBankMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcDistributorBankServiceImpl implements CcDistributorBankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcDistributorBankMapper ccDistributorBankMapper;

    @Override
    public PageBean<CcDistributorBank> findPage(CcDistributorBank eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributorBank> pages =  ccDistributorBankMapper.findList(eo);
        return new PageBean<CcDistributorBank>(pages);
    }

    @Override
    public List<CcDistributorBank> findAll(CcDistributorBank eo) {
        List<CcDistributorBank> list = ccDistributorBankMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributorBank detail(Integer  pk) {
        CcDistributorBank eo = ccDistributorBankMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributorBank eo) {
        CcDistributorBank old = ccDistributorBankMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccDistributorBankMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributorBank eo) {
        int result = ccDistributorBankMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccDistributorBankMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccDistributorBankMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}