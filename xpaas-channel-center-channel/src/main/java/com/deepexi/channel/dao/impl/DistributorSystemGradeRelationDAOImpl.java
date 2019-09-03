package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.DistributorSystemGradeRelationDAO;
import com.deepexi.channel.domain.distributor.DistributorSystemGradeRelationDO;
import com.deepexi.channel.mapper.DistributorSystemGradeRelationMapper;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

@Service
public class DistributorSystemGradeRelationDAOImpl extends ServiceImpl<DistributorSystemGradeRelationMapper, DistributorSystemGradeRelationDO> implements DistributorSystemGradeRelationDAO {

    @Override
    public boolean deleteByGradeIds(List<Long> gradeIdList) {

        return baseMapper.delete(new UpdateWrapper<DistributorSystemGradeRelationDO>().lambda().in(DistributorSystemGradeRelationDO::getDistributorGradeId,gradeIdList))>0;

    }

    @Override
    public boolean deleteBySystemIds(List<Long> SystemIdList) {

        return baseMapper.delete(new UpdateWrapper<DistributorSystemGradeRelationDO>().lambda().in(DistributorSystemGradeRelationDO::getDistributorSystemId,SystemIdList))>0;

    }

    @Override
    public DistributorSystemGradeRelationDO detailByGradeIdAndSystemId(Long gradeId, Long systemId) {

        UpdateWrapper<DistributorSystemGradeRelationDO> wp=new UpdateWrapper();

        wp.set("distributor_grade_id",gradeId);

        wp.set("distributor_system_id",systemId);

        return baseMapper.selectOne(wp);
    }

    @Override
    public List<DistributorSystemGradeRelationDO> listPageByIds(List<Long> idList) {

        return baseMapper.selectBatchIds(idList);
    }
}




