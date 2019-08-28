package com.deepexi.channel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.deepexi.channel.domain.eo.CcStoreGrade;
import com.deepexi.channel.service.CcStoreGradeService;
import com.deepexi.channel.mapper.CcStoreGradeMapper;
import java.util.Arrays;import java.util.List;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import com.deepexi.util.BeanPowerHelper;
import java.util.Arrays;

@Service
public class CcStoreGradeServiceImpl implements CcStoreGradeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CcStoreGradeMapper ccStoreGradeMapper;

    @Override
    public PageBean<CcStoreGrade> findPage(CcStoreGrade eo, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<CcStoreGrade> pages =  ccStoreGradeMapper.findList(eo);
        return new PageBean<CcStoreGrade>(pages);
    }

    @Override
    public List<CcStoreGrade> findAll(CcStoreGrade eo) {
        List<CcStoreGrade> list = ccStoreGradeMapper.findList(eo);
        return list;
    }
    @Override
    public CcStoreGrade detail(Integer  pk) {
        CcStoreGrade eo = ccStoreGradeMapper.selectById(pk);
        return eo;
    }

    @Override
    public Boolean update(Integer  id,CcStoreGrade eo) {
        CcStoreGrade old = ccStoreGradeMapper.selectById(id);
        BeanPowerHelper.mapCompleteOverrider(eo,old); //部分更新
        int result = ccStoreGradeMapper.updateById(old);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean create(CcStoreGrade eo) {
        int result = ccStoreGradeMapper.insert(eo);
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer  pk) {
        int result = ccStoreGradeMapper.deleteBatchIds(Arrays.asList(pk));
        if (result > 0) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Integer ...pks) {
        int result = ccStoreGradeMapper.deleteBatchIds(Arrays.asList(pks));
        if (result > 0) {
            return true;
        }
        return false;
    }

}