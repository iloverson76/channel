package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import com.deepexi.channel.service.DistributorGradeRelationService;
import com.deepexi.channel.mapper.DistributorGradeRelationMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class DistributorGradeRelationServiceImpl implements DistributorGradeRelationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DistributorGradeRelationMapper distributorGradeRelationMapper;

    @Override
    public PageBean<CcDistributorGradeRelation> findPage(CcDistributorGradeRelation eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributorGradeRelation> pages =  distributorGradeRelationMapper.findList(eo);
        return new PageBean<CcDistributorGradeRelation>(pages);
    }

    @Override
    public List<CcDistributorGradeRelation> findAll(CcDistributorGradeRelation eo) {
        List<CcDistributorGradeRelation> list = distributorGradeRelationMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributorGradeRelation detail(Integer  pk) {
        CcDistributorGradeRelation eo = distributorGradeRelationMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributorGradeRelation eo) {
        CcDistributorGradeRelation old = distributorGradeRelationMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = distributorGradeRelationMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributorGradeRelation eo) {
        int result = distributorGradeRelationMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = distributorGradeRelationMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = distributorGradeRelationMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}