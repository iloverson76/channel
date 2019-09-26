package com.deepexi.channel.api.impl;

import com.deepexi.channel.domain.DistributorBankAccountRelationDTO;
import com.deepexi.channel.domain.api.DistributorBankAccountRelationApi;
import com.deepexi.channel.service.DistributorBankAccountRelationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 21:09
 */
public class DistributorBankAccountRelationApiImpl implements DistributorBankAccountRelationApi {

    @Autowired
    DistributorBankAccountRelationService distributorBankAccountRelationService;

    @Override
    public boolean batchCreate(List<DistributorBankAccountRelationDTO> dtoList) {
        return distributorBankAccountRelationService.batchCreate ( dtoList );
    }

    @Override
    public boolean deleteBatchByDistributorIds(List<Long> idList) {
        return distributorBankAccountRelationService.deleteBatchByDistributorIds ( idList );
    }

    @Override
    public int deleteBatchByDistributorId(Long distributorId) {
        return distributorBankAccountRelationService.deleteBatchByDistributorId ( distributorId );
    }

    @Override
    public List<DistributorBankAccountRelationDTO> findAllByDistributorIds(List<Long> distributorIds) {
        return distributorBankAccountRelationService.findAllByDistributorIds ( distributorIds );
    }
}
