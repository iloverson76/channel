package com.deepexi.channel.api.impl;

import com.deepexi.channel.domain.DistributorGradeRelationDTO;
import com.deepexi.channel.api.DistributorGradeRelationApi;
import com.deepexi.channel.service.DistributorGradeRelationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 21:01
 */
public class DistributorGradeRelationApiImpl implements DistributorGradeRelationApi {

    @Autowired
    DistributorGradeRelationService distributorGradeRelationService;

    @Override
    public long create(DistributorGradeRelationDTO dto) {
        return distributorGradeRelationService.create(dto);
    }

    @Override
    public boolean createBatch(List<DistributorGradeRelationDTO> dtoList) {
        return distributorGradeRelationService.createBatch ( dtoList );
    }

    @Override
    public int deleteByDistributorId(long distributorId) {
        return distributorGradeRelationService.deleteByDistributorId ( distributorId );
    }

    @Override
    public int deleteBatchByDistributorIds(List<Long> distributorIdList) {
        return distributorGradeRelationService.deleteBatchByDistributorIds ( distributorIdList );
    }

    @Override
    public DistributorGradeRelationDTO findOne(long distributorId, long gradeId) {
        return distributorGradeRelationService.findOne ( distributorId,gradeId );
    }

    @Override
    public List<DistributorGradeRelationDTO> findAllByDistributorIds(List<Long> distributorIds) {
        return distributorGradeRelationService.findAllByDistributorIds ( distributorIds );
    }

    @Override
    public boolean updateBatchById(List<DistributorGradeRelationDTO> dtoList) {
        return distributorGradeRelationService.updateBatchById ( dtoList );
    }

    @Override
    public boolean updateBatchByDistributorId(List<DistributorGradeRelationDTO> dtoList) {
        return distributorGradeRelationService.updateBatchByDistributorId ( dtoList );
    }

    @Override
    public List<DistributorGradeRelationDTO> findAllByGradeId(Long gradeId) {
        return distributorGradeRelationService.findAllByGradeId ( gradeId );
    }

    @Override
    public List<DistributorGradeRelationDTO> findAllByGradeIds(List<Long> gradeIds) {
        return distributorGradeRelationService.findAllByDistributorIds ( gradeIds );
    }

    @Override
    public List<DistributorGradeRelationDTO> findAllBySystemIds(List<Long> systemIds) {
        return distributorGradeRelationService.findAllBySystemIds ( systemIds );
    }
}
