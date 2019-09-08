package com.deepexi.channel.service;

import com.deepexi.channel.domain.distributor.DistributorGradeRelationDTO;
import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_distributor_grade_relation
 */
public interface DistributorGradeRelationService {

    long create(DistributorGradeRelationDTO dto);

    boolean createBatch(List<DistributorGradeRelationDTO> dtoList);

    int deleteByDistributorId(long distributorId);

    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    DistributorGradeRelationDTO findOne(long distributorId,long gradeId);

    List<DistributorGradeRelationDTO> findAllByDistributorIds(List<Long> distributorIds);

    boolean updateBatchById (List<DistributorGradeRelationDTO> dtoList);

    boolean updateBatchByDistributorId(List<DistributorGradeRelationDTO> dtoList);

    List<DistributorGradeRelationDTO> findAllByGradeId(Long gradeId);

}