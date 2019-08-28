package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import com.deepexi.channel.service.CcDistributorGradeRelationService;
import com.deepexi.channel.mapper.CcDistributorGradeRelationMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcDistributorGradeRelationServiceImpl implements CcDistributorGradeRelationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcDistributorGradeRelationMapper ccDistributorGradeRelationMapper;

    @Override
    public PageBean<CcDistributorGradeRelation> findPage(CcDistributorGradeRelation eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcDistributorGradeRelation> pages =  ccDistributorGradeRelationMapper.findList(eo);
        return new PageBean<CcDistributorGradeRelation>(pages);
    }

    @Override
    public List<CcDistributorGradeRelation> findAll(CcDistributorGradeRelation eo) {
        List<CcDistributorGradeRelation> list = ccDistributorGradeRelationMapper.findList(eo);
        return list;
    }
    @Override
    public CcDistributorGradeRelation detail(Integer  pk) {
        CcDistributorGradeRelation eo = ccDistributorGradeRelationMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcDistributorGradeRelation eo) {
        CcDistributorGradeRelation old = ccDistributorGradeRelationMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccDistributorGradeRelationMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcDistributorGradeRelation eo) {
        int result = ccDistributorGradeRelationMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccDistributorGradeRelationMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccDistributorGradeRelationMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}