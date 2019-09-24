package com.deepexi.channel.service;

import com.deepexi.channel.domain.*;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorBusinessService {

   long create(DistributorDTO dto);

   void validateDistributorCode(String distributorCode);

   void validateDistributorName(String distributorName);

   void validateDistributorNameEn(String distributorNameEn);

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