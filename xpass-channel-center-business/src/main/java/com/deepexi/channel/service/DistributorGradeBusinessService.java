package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorGradeDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;

import java.util.List;

/**
 *
 */
public interface DistributorGradeBusinessService {

    /**
      获取详情
    */
    DistributorGradeDTO detail(Long gradeId);

    /**
     * 分页获取列表
     */
    List<DistributorGradeDTO> findPage(DistributorGradeQuery query);

    /**
     * 可挂载下级的节点--修改
     */
    List<DistributorGradeDTO> findParentNodesForUpdate(Long systemId,Long gradeId);

    /**
     * 可挂载下级的节点-新增
     */
    List<DistributorGradeDTO> findParentNodesForCreate(Long systemId);

    /**
     * 根据体系查询所有的等级
     */
    List<DistributorGradeDTO> findAllGradesBySystem(long systemId);

    /**
     * 批量删除
     */
    Boolean deleteBatchByIds(List<Long> idList,Integer forceDelete);

    void validateHasDistributors(List<Long> gradeIdList);

    void validateHasChildren(List<Long> gradeIdList);

    Boolean deleteDistributors(List<Long> gradeIdList);

    Long create(DistributorGradeDTO dto);

    Boolean update(DistributorGradeDTO dto);



}