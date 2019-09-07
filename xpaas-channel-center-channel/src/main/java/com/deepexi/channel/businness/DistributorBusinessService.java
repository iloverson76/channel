package com.deepexi.channel.businness;

import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.distributor.*;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorBusinessService {

   long create(DistributorDTO dto);

   boolean delete(List<Long> idList);

    List<DistributorDTO> findPage(DistributorQuery query);

    boolean update(DistributorDTO clone);

    List<DistributorGradeDTO> getGradeInfo(Long distributorId);

    List<BankAccountDTO> getBankAccountInfo(Long distributorId);

}