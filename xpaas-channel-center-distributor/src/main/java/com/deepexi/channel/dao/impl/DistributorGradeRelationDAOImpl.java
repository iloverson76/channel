package com.deepexi.channel.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deepexi.channel.dao.DistributorGradeRelationDAO;
import com.deepexi.channel.domain.DistributorGradeRelationDO;
import com.deepexi.channel.domain.DistributorGradeRelationDTO;
import com.deepexi.channel.mapper.DistributorGradeRelationMapper;
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

        wp.eq("grade_id",gradeId);

        return baseMapper.selectOne(wp);
    }

    @Override
    public List<DistributorGradeRelationDO> findAllByGradeId(Long gradeId) {

        QueryWrapper<DistributorGradeRelationDO> wp=new QueryWrapper<>();

        wp.eq("grade_id",gradeId);

        return baseMapper.selectList(wp);
    }

    @Override
    public List<DistributorGradeRelationDO> findAllByGradeIds(List<Long> gradeIds) {

        QueryWrapper<DistributorGradeRelationDO> wp=new QueryWrapper<>();

        wp.in("grade_id",gradeIds);

        return baseMapper.selectList(wp);
    }

    @Override
    public List<DistributorGradeRelationDO> findAllBySystemIds(List<Long> systemIds) {

        QueryWrapper<DistributorGradeRelationDO> wp=new QueryWrapper<>();

        wp.in("system_id",systemIds);

        return baseMapper.selectList(wp);
    }

    @Override
    public List<DistributorGradeRelationDO> findAllByDistributorParentIds(List<Long> distributorParentIds) {

        QueryWrapper<DistributorGradeRelationDO> wp=new QueryWrapper<>();

        wp.in("parent_id",distributorParentIds);

        return baseMapper.selectList(wp);

    }
}
