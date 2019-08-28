package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreGradeRelation;
import com.deepexi.channel.service.CcStoreGradeRelationService;
import com.deepexi.channel.mapper.CcStoreGradeRelationMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcStoreGradeRelationServiceImpl implements CcStoreGradeRelationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcStoreGradeRelationMapper ccStoreGradeRelationMapper;

    @Override
    public PageBean<CcStoreGradeRelation> findPage(CcStoreGradeRelation eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreGradeRelation> pages =  ccStoreGradeRelationMapper.findList(eo);
        return new PageBean<CcStoreGradeRelation>(pages);
    }

    @Override
    public List<CcStoreGradeRelation> findAll(CcStoreGradeRelation eo) {
        List<CcStoreGradeRelation> list = ccStoreGradeRelationMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreGradeRelation detail(Integer  pk) {
        CcStoreGradeRelation eo = ccStoreGradeRelationMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreGradeRelation eo) {
        CcStoreGradeRelation old = ccStoreGradeRelationMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccStoreGradeRelationMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreGradeRelation eo) {
        int result = ccStoreGradeRelationMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccStoreGradeRelationMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccStoreGradeRelationMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}