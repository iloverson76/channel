package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.DistributorSystemBusinessApi;
import com.deepexi.channel.domain.DistributorGradeSystemBusiDTO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;
import com.deepexi.channel.domain.StoreDistributorDTO;
import com.deepexi.channel.service.DistributorSystemBusinessService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-27 21:14
 */
public class DistributorSystemBusinessApiImpl implements DistributorSystemBusinessApi {

    @Autowired
    DistributorSystemBusinessService distributorSystemBusinessService;

    @Override
    public Long create(DistributorGradeSystemBusiDTO dto) {
        return distributorSystemBusinessService.create ( dto );
    }

    @Override
    public List<DistributorGradeSystemBusiDTO> findPage(DistributorGradeSystemQuery query) {
        return null;
    }

    @Override
    public DistributorGradeSystemBusiDTO detail(Long pk) {
        return null;
    }

    @Override
    public List<StoreDistributorDTO> getDistributorGradeSystemByDistributorId(long distributorId) {
        return null;
    }

    @Override
    public boolean deleteBatchByIds(List<Long> idList, Integer forceDelete) {
        return false;
    }

    @Override
    public void validateHasGrades(List<Long> systemIdList) {

    }

    @Override
    public void validateGradeSystemCode(String systemCode) {

    }

    @Override
    public void validateGradeSystemName(String systemName) {

    }

    @Override
    public void validateGradeSystemNameEn(String systemNameEn) {

    }

    @Override
    public Boolean update(DistributorGradeSystemBusiDTO dto) {
        return null;
    }
}
