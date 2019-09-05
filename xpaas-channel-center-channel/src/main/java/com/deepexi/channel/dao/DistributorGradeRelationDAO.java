package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.distributor.DistributorGradeDO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;
import com.deepexi.channel.domain.distributor.DistributorGradeRelationDO;
import com.deepexi.channel.domain.distributor.DistributorGradeRelationDTO;

import java.util.List;

public interface DistributorGradeRelationDAO extends  IService<DistributorGradeRelationDO> {


    int deleteByDistributorId(long distributorId);

    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    boolean updateById(DistributorGradeRelationDO eo);

    boolean updateBatchById(List<DistributorGradeRelationDO> eoList);

    List<DistributorGradeRelationDO> findAllByDistributorIds(long distributorId);

    DistributorGradeRelationDO findOne(long distributorId, long gradeId);
}
