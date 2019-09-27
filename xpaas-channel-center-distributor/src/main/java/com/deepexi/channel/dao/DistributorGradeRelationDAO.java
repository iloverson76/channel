package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.DistributorGradeRelationDO;

import java.util.List;

public interface DistributorGradeRelationDAO extends  IService<DistributorGradeRelationDO> {


    int deleteByDistributorId(long distributorId);

    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    boolean updateById(DistributorGradeRelationDO eo);

    boolean updateBatchById(List<DistributorGradeRelationDO> eoList);

    boolean updateBatchByDistributorIds(List<DistributorGradeRelationDO> eoList);

    List<DistributorGradeRelationDO> findAllByDistributorIds(List<Long> distributorIds);

    DistributorGradeRelationDO findOne(long distributorId, long gradeId);

    List<DistributorGradeRelationDO> findAllByGradeId(Long gradeId);

    List<DistributorGradeRelationDO> findAllByGradeIds(List<Long> gradeIds);

    List<DistributorGradeRelationDO> findAllBySystemIds(List<Long> systemIds);

    List<DistributorGradeRelationDO> findAllByDistributorParentIds(List<Long> distributorParentIds);
}
