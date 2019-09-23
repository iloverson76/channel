package com.deepexi.channel.businness;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.distributor.*;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorBusinessService {

   long create(DistributorDTO dto);

   boolean deleteBatchByIds(List<Long> idList,Integer forceDelete);

   void validateHasChildren(List<Long> butorIdList);

   void validateHasStores(List<Long> butorIdList);

    boolean deleteStores(List<Long> distributorIdList);

    List<DistributorDTO> findPage(DistributorQuery query);

    boolean update(DistributorDTO clone);

    List<AreaDTO> getAreaInfo(Long distributorId);

    List<BankAccountDTO> getBankAccountInfo(Long distributorId);

    DistributorDTO detail(Long id);

    List<DistributorDTO> listParentDistributorsByGrade(Long gradeId);

    List<GradeInfoDTO> getGradeInfo(Long distributorId);
}