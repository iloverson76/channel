package com.deepexi.channel.businness;

import com.deepexi.channel.domain.distributor.DistributorGradeDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeQuery;

import java.util.List;

/**
 * cc_distributor_grade
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

    boolean delete(List<Long> idList);

    Long create(DistributorGradeDTO dto);

}