package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorGradeRelationDTO;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorGradeRelationService {

    /**
     * 创建经销商与等级的关联关系
     * @param dto 新增实体
     * @return 新纪录ID
     */
    long create(DistributorGradeRelationDTO dto);

    /**
     * 批量创建经销商与等级的关联关系
     * @param dtoList 新增实体集合
     * @return 是否成功
     */
    boolean createBatch(List<DistributorGradeRelationDTO> dtoList);

    /**
     * 根据经销商ID删除与等级的关联关系
     * @param distributorId 经销商ID
     * @return 成功删除的记录数量
     */
    int deleteByDistributorId(long distributorId);

    /**
     * 根据经销商ID集合批量删除与等级的关联关系
     * @param distributorIdList
     * @return 成功删除的记录数量
     */
    int deleteBatchByDistributorIds(List<Long> distributorIdList);

    /**
     * 根据经销商ID和等级ID查找关联关系
     * @param distributorId 经销商ID
     * @param gradeId 等级ID
     * @return 关联关系
     */
    DistributorGradeRelationDTO findOne(long distributorId,long gradeId);

    /**
     * 根据经销商ID集合查找与等级的所有关联关系
     * @param distributorIds 经销商ID集合
     * @return 关联关系集合
     */
    List<DistributorGradeRelationDTO> findAllByDistributorIds(List<Long> distributorIds);

    /**
     * 根据经销商父级ID集合查找与等级的所有关联关系
     * @param distributorParentIds 经销商ID集合
     * @return 关联关系集合
     */
    List<DistributorGradeRelationDTO> findAllByDistributorParentIds(List<Long> distributorParentIds);

    /**
     * 根据实体ID集合批量更新经销商与等级的关联关系
     * @param dtoList 实体集合
     * @return 成功删除的记录数量
     */
    boolean updateBatchById (List<DistributorGradeRelationDTO> dtoList);

    /**
     * 根据实体ID集合批量删除经销商与等级的关联关系
     * @param dtoList 实体集合
     * @return 成功删除的记录数量
     */
    boolean updateBatchByDistributorId(List<DistributorGradeRelationDTO> dtoList);

    /**
     * 根据等级ID查找所有与经销商的关联关系
     * @param gradeId 等级ID
     * @return 关联关系集合
     */
    List<DistributorGradeRelationDTO> findAllByGradeId(Long gradeId);

    /**
     * 根据等级ID集合查找所有与经销商的关联关系
     * @param gradeIds 等级ID集合
     * @return 关联关系集合
     */
    List<DistributorGradeRelationDTO> findAllByGradeIds(List<Long> gradeIds);

    /**
     * 根据体系ID查找所有与经销商的关联关系
     * @param systemIds 体系ID集合
     * @return 关联关系集合
     */
    List<DistributorGradeRelationDTO> findAllBySystemIds(List<Long> systemIds);



}