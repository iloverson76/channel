package com.deepexi.channel.api.impl;

import com.deepexi.channel.domain.DistributorAreaRelationDTO;
import com.deepexi.channel.api.DistributorAreaRelationApi;
import com.deepexi.channel.service.DistributorAreaRelationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 20:49
 */
public class DistributorAreaRelationApiImpl implements DistributorAreaRelationApi {

    @Autowired
    DistributorAreaRelationService distributorAreaRelationService;

    @Override
    public Long create(DistributorAreaRelationDTO dto) {
        return distributorAreaRelationService.create(dto);
    }

    @Override
    public boolean createBatch(List<DistributorAreaRelationDTO> dtoList) {
        return distributorAreaRelationService.createBatch ( dtoList );
    }

    @Override
    public int deleteBatchByDistributorIds(List<Long> distributorIdList) {
        return distributorAreaRelationService.deleteBatchByDistributorIds ( distributorIdList );
    }

    @Override
    public int deleteBatchByAreaIds(List<Long> areaIdList) {
        return distributorAreaRelationService.deleteBatchByAreaIds ( areaIdList );
    }

    @Override
    public List<DistributorAreaRelationDTO> findAllByDistributorId(Long butorId) {
        return distributorAreaRelationService.findAllByDistributorId ( butorId );
    }

    @Override
    public List<DistributorAreaRelationDTO> findAllByAreaIds(List<Long> areaIdList) {
        return distributorAreaRelationService.findAllByAreaIds ( areaIdList );
    }

    @Override
    public Boolean deleteByDistributorId(Long distributorId) {
        return distributorAreaRelationService.deleteByDistributorId ( distributorId );
    }
}
