package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.DistributorBusinessApi;
import com.deepexi.channel.domain.*;
import com.deepexi.channel.service.DistributorBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-27 15:52
 */
@RestController
public class DistributorBusinessApiImpl implements DistributorBusinessApi {

    @Autowired
    DistributorBusinessService distributorBusinessService;

    @Override
    public long create(DistributorBusiDTO dto) {
        return distributorBusinessService.create ( dto );
    }

    @Override
    public void validateDistributorCode(String distributorCode) {
        distributorBusinessService.validateDistributorCode ( distributorCode );
    }

    @Override
    public void validateDistributorName(String distributorName) {
        distributorBusinessService.validateDistributorName ( distributorName );
    }

    @Override
    public void validateDistributorNameEn(String distributorNameEn) {
        distributorBusinessService.validateDistributorNameEn ( distributorNameEn );
    }

    @Override
    public boolean deleteBatchByIds(List<Long> idList, Integer forceDelete) {
        return distributorBusinessService.deleteBatchByIds ( idList,forceDelete );
    }

    @Override
    public void validateHasChildren(List<Long> distributorIdList) {
        distributorBusinessService.validateHasChildren ( distributorIdList );
    }

    @Override
    public void validateHasStores(List<Long> butorIdList) {
        distributorBusinessService.validateHasStores ( butorIdList );
    }

    @Override
    public boolean deleteStores(List<Long> distributorIdList) {
        return distributorBusinessService.deleteStores ( distributorIdList );
    }

    @Override
    public List<DistributorBusiDTO> findPage(DistributorQuery query) {
        return distributorBusinessService.findPage ( query );
    }

    @Override
    public boolean update(DistributorBusiDTO dto) {
        return distributorBusinessService.update ( dto );
    }

    @Override
    public List<AreaDTO> getAreaInfo(Long distributorId) {
        return distributorBusinessService.getAreaInfo ( distributorId );
    }

    @Override
    public List<BankAccountDTO> getBankAccountInfo(Long distributorId) {
        return distributorBusinessService.getBankAccountInfo ( distributorId );
    }

    @Override
    public DistributorBusiDTO detail(Long id) {
        return distributorBusinessService.detail ( id );
    }

    @Override
    public List<DistributorBusiDTO> listParentDistributorsByGrade(Long gradeId) {
        return distributorBusinessService.listParentDistributorsByGrade ( gradeId );
    }

    @Override
    public List<GradeInfoDTO> getGradeInfo(Long distributorId) {
        return distributorBusinessService.getGradeInfo ( distributorId );
    }
}
