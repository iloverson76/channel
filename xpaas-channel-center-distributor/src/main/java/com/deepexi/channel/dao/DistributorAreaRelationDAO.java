package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.DistributorAreaRelationDO;

import java.util.List;

public interface DistributorAreaRelationDAO extends  IService<DistributorAreaRelationDO> {

    int deleteByDistributorId(long distributorId);

    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    boolean updateById(DistributorAreaRelationDO eo);

    boolean updateBatchById(List<DistributorAreaRelationDO> eoList);

    List<DistributorAreaRelationDO> findAllByDistributorId(Long distributorId);

    DistributorAreaRelationDO getOne(Long distributorId);

    List<DistributorAreaRelationDO> findAllByAreaIds(List<Long> areaIdList);

    int deleteBatchByAreaIds(List<Long> areaIdList);
}
