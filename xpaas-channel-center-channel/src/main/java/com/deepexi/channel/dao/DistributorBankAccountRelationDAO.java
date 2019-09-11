package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDO;
import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDO;

import java.util.List;

public interface DistributorBankAccountRelationDAO extends  IService<DistributorBankAccountRelationDO> {

    int deleteByDistributorId(long distributorId);

    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    int deleteBatchByDistributorId(Long distributorId);

    boolean updateById(DistributorBankAccountRelationDO eo);

    boolean updateBatchById(List<DistributorBankAccountRelationDO> eoList);

    List<DistributorBankAccountRelationDO> findAllByDistributorIds(List<Long> distributorIds);

    DistributorBankAccountRelationDO findOne(long distributorId, long BankAccountId);
    
}
