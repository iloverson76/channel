package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorGradeSystem;
import com.deepexi.channel.service.CcDistributorGradeSystemService;
import com.deepexi.channel.mapper.CcDistributorGradeSystemMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcDistributorGradeSystemServiceImpl implements CcDistributorGradeSystemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcDistributorGradeSystemMapper ccDistributorGradeSystemMapper;

    @Override
    public PageBean<CcDistributorGradeSystem> findPage(CcDistributorGradeSystem eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributorGradeSystem> pages =  ccDistributorGradeSystemMapper.findList(eo);
        return new PageBean<CcDistributorGradeSystem>(pages);
    }

    @Override
    public List<CcDistributorGradeSystem> findAll(CcDistributorGradeSystem eo) {
        List<CcDistributorGradeSystem> list = ccDistributorGradeSystemMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributorGradeSystem detail(Integer  pk) {
        CcDistributorGradeSystem eo = ccDistributorGradeSystemMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributorGradeSystem eo) {
        CcDistributorGradeSystem old = ccDistributorGradeSystemMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccDistributorGradeSystemMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributorGradeSystem eo) {
        int result = ccDistributorGradeSystemMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccDistributorGradeSystemMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccDistributorGradeSystemMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}