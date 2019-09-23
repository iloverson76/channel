package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorAreaRelationDTO;

import java.util.List;


public interface DistributorAreaRelationService {

    Long create(DistributorAreaRelationDTO dto);

    boolean createBatch(List<DistributorAreaRelationDTO> dtoList);

    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    List<DistributorAreaRelationDTO> findAllByDistributorId(Long butorId);

    Boolean deleteByDistributorId(Long distributorId);
}