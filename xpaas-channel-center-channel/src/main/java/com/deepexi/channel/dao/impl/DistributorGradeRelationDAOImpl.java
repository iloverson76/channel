package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.DistributorGradeDAO;
import com.deepexi.channel.dao.DistributorGradeRelationDAO;
import com.deepexi.channel.domain.distributor.*;
import com.deepexi.channel.mapper.DistributorGradeMapper;
import com.deepexi.channel.mapper.DistributorGradeRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chp
 * @date 2019/8/23
 **/
@Repository
public class DistributorGradeRelationDAOImpl extends ServiceImpl<DistributorGradeRelationMapper, DistributorGradeRelationDO> implements DistributorGradeRelationDAO {

    @Override
    public int deleteByDistributorId(long distributorId) {

        UpdateWrapper<DistributorGradeRelationDO> wp=new UpdateWrapper<>();

        wp.eq("distributor_id",distributorId);

        return baseMapper.delete(wp);
    }

    @Override
    public int deleteBatchByDistributorIds(List<Long> distributorIdList) {

        UpdateWrapper<DistributorGradeRelationDO> wp=new UpdateWrapper<>();

        wp.in("distributor_id",distributorIdList);

        return baseMapper.delete(wp);

    }

    @Override
    public boolean updateById(DistributorGradeRelationDO eo){

       return updateById(eo);
    }

    @Override
    public boolean updateBatchById(List<DistributorGradeRelationDO> eoList) {

        return updateBatchById(eoList);
    }

    @Override
    public boolean updateBatchByDistributorIds(List<DistributorGradeRelationDO> eoList) {
        return false;
    }

    @Override
    public List<DistributorGradeRelationDO> findAllByDistributorIds(List<Long> distributorIds) {

        QueryWrapper<DistributorGradeRelationDO> wp=new QueryWrapper<>();

        wp.in("distributor_id",distributorIds);

        return baseMapper.selectList(wp);
    }

    @Override
    public DistributorGradeRelationDO findOne(long distributorId, long gradeId) {

        QueryWrapper<DistributorGradeRelationDO> wp=new QueryWrapper<>();

        wp.eq("distributor_id",distributorId);

        wp.eq("distributor_grade_id",gradeId);

        return baseMapper.selectOne(wp);
    }
}
