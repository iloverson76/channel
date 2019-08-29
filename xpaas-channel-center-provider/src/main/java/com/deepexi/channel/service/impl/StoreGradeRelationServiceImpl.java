package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreGradeRelation;
import com.deepexi.channel.service.StoreGradeRelationService;
import com.deepexi.channel.mapper.StoreGradeRelationMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class StoreGradeRelationServiceImpl implements StoreGradeRelationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreGradeRelationMapper storeGradeRelationMapper;

    @Override
    public PageBean<CcStoreGradeRelation> findPage(CcStoreGradeRelation eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreGradeRelation> pages =  storeGradeRelationMapper.findList(eo);
        return new PageBean<CcStoreGradeRelation>(pages);
    }

    @Override
    public List<CcStoreGradeRelation> findAll(CcStoreGradeRelation eo) {
        List<CcStoreGradeRelation> list = storeGradeRelationMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreGradeRelation detail(Integer  pk) {
        CcStoreGradeRelation eo = storeGradeRelationMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreGradeRelation eo) {
        CcStoreGradeRelation old = storeGradeRelationMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = storeGradeRelationMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreGradeRelation eo) {
        int result = storeGradeRelationMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = storeGradeRelationMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = storeGradeRelationMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}