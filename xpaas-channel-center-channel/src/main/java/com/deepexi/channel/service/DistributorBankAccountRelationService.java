package com.deepexi.channel.service;

import com.deepexi.channel.domain.distributor.DistributorBankAccountRelationDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeRelationDTO;

import java.util.List;

/**
 * cc_distributor_BankAccount_relation
 */
public interface DistributorBankAccountRelationService {

    boolean batchCreate(List<DistributorBankAccountRelationDTO> dtoList);

    boolean deleteBatchByDistributorIds(List<Long> idList);

    int deleteBatchByDistributorId(Long distributorId);

    List<DistributorBankAccountRelationDTO> findAllByDistributorIds(List<Long> bankAccountIds);

}