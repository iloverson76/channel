package com.deepexi.channel.api.impl;

import com.deepexi.channel.api.DistributorGradeBusinessApi;
import com.deepexi.channel.domain.DistributorGradeBusiDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;
import com.deepexi.channel.service.DistributorGradeBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-27 21:02
 */
@RestController
public class DistributorGradeBusinessApiImpl implements DistributorGradeBusinessApi {

    @Autowired
    DistributorGradeBusinessService distributorGradeBusinessService;

    @Override
    public DistributorGradeBusiDTO detail(Long gradeId) {
        return distributorGradeBusinessService.detail ( gradeId );
    }

    @Override
    public List<DistributorGradeBusiDTO> findPage(DistributorGradeQuery query) {
        return distributorGradeBusinessService.findPage ( query );
    }

    @Override
    public List<DistributorGradeBusiDTO> findParentNodesForUpdate(Long systemId, Long gradeId) {
        return distributorGradeBusinessService.findParentNodesForUpdate ( systemId,gradeId );
    }

    @Override
    public List<DistributorGradeBusiDTO> findParentNodesForCreate(Long systemId) {
        return distributorGradeBusinessService.findParentNodesForCreate ( systemId );
    }

    @Override
    public List<DistributorGradeBusiDTO> findAllGradesBySystem(long systemId) {
        return distributorGradeBusinessService.findAllGradesBySystem ( systemId );
    }

    @Override
    public Boolean deleteBatchByIds(List<Long> idList, Integer forceDelete) {
        return distributorGradeBusinessService.deleteBatchByIds ( idList,forceDelete );
    }

    @Override
    public void validateHasDistributors(List<Long> gradeIdList) {
        distributorGradeBusinessService.validateHasDistributors ( gradeIdList );
    }

    @Override
    public void validateHasChildren(List<Long> gradeIdList) {
        distributorGradeBusinessService.validateHasChildren ( gradeIdList );
    }

    @Override
    public Boolean deleteDistributors(List<Long> gradeIdList) {
        return distributorGradeBusinessService.deleteDistributors ( gradeIdList );
    }

    @Override
    public Long create(DistributorGradeBusiDTO dto) {
        return distributorGradeBusinessService.create ( dto );
    }

    @Override
    public Boolean update(DistributorGradeBusiDTO dto) {
        return distributorGradeBusinessService.update ( dto );
    }

    @Override
    public List<DistributorGradeBusiDTO> listChildrenNodes(Long id) {
        return distributorGradeBusinessService.listChildrenNodes ( id );
    }

    @Override
    public void validateDistributorGradeCode(String gradeCode, Long systemId) {
        distributorGradeBusinessService.validateDistributorGradeCode ( gradeCode,systemId );
    }

    @Override
    public void validateDistributorGradeName(String gradeName, Long systemId) {
        distributorGradeBusinessService.validateDistributorGradeName ( gradeName,systemId );
    }

    @Override
    public void validateDistributorGradeNameEn(String gradeNameEn, Long systemId) {
        distributorGradeBusinessService.validateDistributorGradeNameEn ( gradeNameEn,systemId );
    }
}
