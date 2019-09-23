package com.deepexi.channel.service;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.distributor.DistributorAreaRelationDTO;

import java.util.List;


public interface DistributorAreaRelationService {

    Long create(DistributorAreaRelationDTO dto);

    boolean createBatch(List<DistributorAreaRelationDTO> dtoList);

    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    int deleteBatchByAreaIds(List<Long> areaIdList);

    List<DistributorAreaRelationDTO> findAllByDistributorId(Long butorId);

    List<DistributorAreaRelationDTO> findAllByAreaIds(List<Long> areaIdList);

    Boolean deleteByDistributorId(Long distributorId);
}