package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreDistributorRelation;
import com.deepexi.channel.service.CcStoreDistributorRelationService;
import com.deepexi.channel.mapper.CcStoreDistributorRelationMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcStoreDistributorRelationServiceImpl implements CcStoreDistributorRelationService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcStoreDistributorRelationMapper ccStoreDistributorRelationMapper;

    @Override
    public PageBean<CcStoreDistributorRelation> findPage(CcStoreDistributorRelation eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreDistributorRelation> pages =  ccStoreDistributorRelationMapper.findList(eo);
        return new PageBean<CcStoreDistributorRelation>(pages);
    }

    @Override
    public List<CcStoreDistributorRelation> findAll(CcStoreDistributorRelation eo) {
        List<CcStoreDistributorRelation> list = ccStoreDistributorRelationMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreDistributorRelation detail(Integer  pk) {
        CcStoreDistributorRelation eo = ccStoreDistributorRelationMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreDistributorRelation eo) {
        CcStoreDistributorRelation old = ccStoreDistributorRelationMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccStoreDistributorRelationMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreDistributorRelation eo) {
        int result = ccStoreDistributorRelationMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccStoreDistributorRelationMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccStoreDistributorRelationMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}