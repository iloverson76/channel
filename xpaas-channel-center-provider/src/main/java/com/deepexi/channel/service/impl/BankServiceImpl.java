package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcBank;
import com.deepexi.channel.service.BankService;
import com.deepexi.channel.mapper.BankMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class BankServiceImpl implements BankService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BankMapper bankMapper;

    @Override
    public PageBean<CcBank> findPage(CcBank eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcBank> pages =  bankMapper.findList(eo);
        return new PageBean<CcBank>(pages);
    }

    @Override
    public List<CcBank> findAll(CcBank eo) {
        List<CcBank> list = bankMapper.findList(eo);
        return list;
    }
    @Override
    public CcBank detail(Integer  pk) {
        CcBank eo = bankMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcBank eo) {
        CcBank old = bankMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = bankMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcBank eo) {
        int result = bankMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = bankMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = bankMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}