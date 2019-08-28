package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreTypeRelation;
import com.deepexi.channel.service.CcStoreTypeRelationService;
import com.deepexi.channel.mapper.CcStoreTypeRelationMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcStoreTypeRelationServiceImpl implements CcStoreTypeRelationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcStoreTypeRelationMapper ccStoreTypeRelationMapper;

    @Override
    public PageBean<CcStoreTypeRelation> findPage(CcStoreTypeRelation eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreTypeRelation> pages =  ccStoreTypeRelationMapper.findList(eo);
        return new PageBean<CcStoreTypeRelation>(pages);
    }

    @Override
    public List<CcStoreTypeRelation> findAll(CcStoreTypeRelation eo) {
        List<CcStoreTypeRelation> list = ccStoreTypeRelationMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreTypeRelation detail(Integer  pk) {
        CcStoreTypeRelation eo = ccStoreTypeRelationMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreTypeRelation eo) {
        CcStoreTypeRelation old = ccStoreTypeRelationMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccStoreTypeRelationMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreTypeRelation eo) {
        int result = ccStoreTypeRelationMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccStoreTypeRelationMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccStoreTypeRelationMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}