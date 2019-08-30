package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreGrade;
import com.deepexi.channel.service.StoreGradeService;
import com.deepexi.channel.mapper.StoreGradeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;

@Service
public class StoreGradeServiceImpl implements StoreGradeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StoreGradeMapper storeGradeMapper;

    @Override
    public PageBean<CcStoreGrade> findPage(CcStoreGrade eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreGrade> pages =  storeGradeMapper.findList(eo);
        return new PageBean<CcStoreGrade>(pages);
    }

    @Override
    public List<CcStoreGrade> findAll(CcStoreGrade eo) {
        List<CcStoreGrade> list = storeGradeMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreGrade detail(Integer  pk) {
        CcStoreGrade eo = storeGradeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreGrade eo) {
        CcStoreGrade old = storeGradeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = storeGradeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreGrade eo) {
        int result = storeGradeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = storeGradeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = storeGradeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}