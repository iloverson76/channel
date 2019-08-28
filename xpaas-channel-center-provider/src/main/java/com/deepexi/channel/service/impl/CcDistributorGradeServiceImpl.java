package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorGrade;
import com.deepexi.channel.service.CcDistributorGradeService;
import com.deepexi.channel.mapper.CcDistributorGradeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcDistributorGradeServiceImpl implements CcDistributorGradeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcDistributorGradeMapper ccDistributorGradeMapper;

    @Override
    public PageBean<CcDistributorGrade> findPage(CcDistributorGrade eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributorGrade> pages =  ccDistributorGradeMapper.findList(eo);
        return new PageBean<CcDistributorGrade>(pages);
    }

    @Override
    public List<CcDistributorGrade> findAll(CcDistributorGrade eo) {
        List<CcDistributorGrade> list = ccDistributorGradeMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributorGrade detail(Integer  pk) {
        CcDistributorGrade eo = ccDistributorGradeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributorGrade eo) {
        CcDistributorGrade old = ccDistributorGradeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccDistributorGradeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributorGrade eo) {
        int result = ccDistributorGradeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccDistributorGradeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccDistributorGradeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}