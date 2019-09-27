package com.deepexi.channel.api.impl;

import com.deepexi.channel.domain.DistributorGradeDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;
import com.deepexi.channel.api.DistributorGradeApi;
import com.deepexi.channel.service.DistributorGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 20:31
 */
@RestController
public class DistributorGradeApiImpl implements DistributorGradeApi {

    @Autowired
    DistributorGradeService distributorGradeService;


    @Override
    public Long create(DistributorGradeDTO dto) {
        return distributorGradeService.create ( dto );
    }

    @Override
    public DistributorGradeDTO getById(Long pk) {
        return distributorGradeService.getById ( pk );
    }

    @Override
    public Boolean updateById(DistributorGradeDTO dto) {
        return distributorGradeService.updateById ( dto );
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> ids) {
        return distributorGradeService.deleteBatchByIds ( ids );
    }

    @Override
    public Boolean deleteById(Long id) {
        return distributorGradeService.deleteById ( id );
    }

    @Override
    public List<DistributorGradeDTO> findPage(DistributorGradeQuery query) {
        return distributorGradeService.findPage ( query );
    }

    @Override
    public List<DistributorGradeDTO> findAllBySystem(Long systemId) {
        return distributorGradeService.findAllBySystem ( systemId );
    }

    @Override
    public boolean updateBatchById(List<DistributorGradeDTO> dtoList) {
        return distributorGradeService.updateBatchById ( dtoList );
    }

    @Override
    public List<String> listDistributorGradeCode(Long systemId) {
        return distributorGradeService.listDistributorGradeCode ( systemId );
    }

    @Override
    public List<String> listDistributorGradeName(Long systemId) {
        return distributorGradeService.listDistributorGradeCode ( systemId );
    }

    @Override
    public List<String> listDistributorGradeNameEn(Long systemId) {
        return distributorGradeService.listDistributorGradeNameEn ( systemId );
    }
}
