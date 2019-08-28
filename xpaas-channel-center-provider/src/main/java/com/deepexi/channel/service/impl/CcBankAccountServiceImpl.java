package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcBankAccount;
import com.deepexi.channel.service.CcBankAccountService;
import com.deepexi.channel.mapper.CcBankAccountMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcBankAccountServiceImpl implements CcBankAccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcBankAccountMapper ccBankAccountMapper;

    @Override
    public PageBean<CcBankAccount> findPage(CcBankAccount eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcBankAccount> pages =  ccBankAccountMapper.findList(eo);
        return new PageBean<CcBankAccount>(pages);
    }

    @Override
    public List<CcBankAccount> findAll(CcBankAccount eo) {
        List<CcBankAccount> list = ccBankAccountMapper.findList(eo);
        return list;
    }
    @Override
    public CcBankAccount detail(Integer  pk) {
        CcBankAccount eo = ccBankAccountMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcBankAccount eo) {
        CcBankAccount old = ccBankAccountMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccBankAccountMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcBankAccount eo) {
        int result = ccBankAccountMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccBankAccountMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccBankAccountMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}