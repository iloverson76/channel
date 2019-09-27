package com.deepexi.channel.api.impl;

import com.deepexi.channel.domain.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;
import com.deepexi.channel.api.DistributorGradeSystemApi;
import com.deepexi.channel.service.DistributorGradeSystemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 20:43
 */
public class DistributorGradeSystemApiImpl implements DistributorGradeSystemApi {

    @Autowired
    DistributorGradeSystemService distributorGradeSystemService;

    @Override
    public long create(DistributorGradeSystemDTO dto) {
        return distributorGradeSystemService.create ( dto );
    }

    @Override
    public Boolean update(DistributorGradeSystemDTO dto) {
        return distributorGradeSystemService.update ( dto );
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> idList) {
        return distributorGradeSystemService.deleteBatchByIds ( idList );
    }

    @Override
    public DistributorGradeSystemDTO detail(Long pk) {
        return distributorGradeSystemService.detail ( pk );
    }

    @Override
    public DistributorGradeSystemDTO getById(Long id) {
        return distributorGradeSystemService.getById ( id );
    }

    @Override
    public List<DistributorGradeSystemDTO> findPage(DistributorGradeSystemQuery query) {
        return distributorGradeSystemService.findPage ( query );
    }

    @Override
    public List<String> listGradeSystemCode() {
        return distributorGradeSystemService.listGradeSystemCode ();
    }

    @Override
    public List<String> listGradeSystemName() {
        return distributorGradeSystemService.listGradeSystemName ();
    }

    @Override
    public List<String> listGradeSystemNameEn() {
        return distributorGradeSystemService.listGradeSystemNameEn ();
    }
}
