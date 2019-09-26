package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorAreaRelationDTO;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorAreaRelationService {
    /**
     * 获取经销商和区域关联关系
     * @param dto 新增实体
     * @return 新增记录ID
     */
    Long create(DistributorAreaRelationDTO dto);

    /**
     * 批量新增经销商和区域关联关系
     * @param dtoList 新增实体集合
     * @return 是否成功
     */
    boolean createBatch(List<DistributorAreaRelationDTO> dtoList);

    /**
     * 根据经销商ID集合批量删除与区域的关联关系
     * @param distributorIdList 经销商ID集合
     * @return 成功删除的记录数
     */
    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    /**
     * 根据区域ID集合删除与经销商的关联关系
     * @param areaIdList 区域ID集合
     * @return 成功删除的记录数
     */
    int deleteBatchByAreaIds(List<Long> areaIdList);

    /**
     * 根据经销商ID查找所有与区域的关联关系
     * @param butorId 经销商ID
     * @return 关联关系集合
     */
    List<DistributorAreaRelationDTO> findAllByDistributorId(Long butorId);

    /**
     * 根据区域ID集合查找所有与经销商的关联关系
     * @param areaIdList 区域ID集合
     * @return 关联关系集合
     */
    List<DistributorAreaRelationDTO> findAllByAreaIds(List<Long> areaIdList);

    /**
     * 根据经销商ID集合删除所有与区域的关联关系
     * @param distributorId
     * @return 成功删除的记录数
     */
    Boolean deleteByDistributorId(Long distributorId);
}