package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDO;

import java.util.List;

public interface DistributorAreaRelationDAO extends  IService<DistributorAreaRelationDO> {

    int deleteByDistributorId(long distributorId);

    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    boolean updateById(DistributorAreaRelationDO eo);

    boolean updateBatchById(List<DistributorAreaRelationDO> eoList);

    List<DistributorAreaRelationDO> findAllByDistributorIds(long distributorId);

    DistributorAreaRelationDO findOne(long distributorId, long gradeId);

}
