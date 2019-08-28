package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorGrade;
import com.deepexi.channel.service.DistributorGradeService;
import com.deepexi.channel.mapper.DistributorGradeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class DistributorGradeServiceImpl implements DistributorGradeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DistributorGradeMapper distributorGradeMapper;

    @Override
    public PageBean<CcDistributorGrade> findPage(CcDistributorGrade eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributorGrade> pages =  distributorGradeMapper.findList(eo);
        return new PageBean<CcDistributorGrade>(pages);
    }

    @Override
    public List<CcDistributorGrade> findAll(CcDistributorGrade eo) {
        List<CcDistributorGrade> list = distributorGradeMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributorGrade detail(Integer  pk) {
        CcDistributorGrade eo = distributorGradeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributorGrade eo) {
        CcDistributorGrade old = distributorGradeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = distributorGradeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributorGrade eo) {
        int result = distributorGradeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = distributorGradeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = distributorGradeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}