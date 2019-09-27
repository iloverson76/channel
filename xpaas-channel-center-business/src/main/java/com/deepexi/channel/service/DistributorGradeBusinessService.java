package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorGradeBusiDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorGradeBusinessService {

    /**
     * 获取经销商等级详情
     * @param gradeId 等级ID
     * @return
     */
    DistributorGradeBusiDTO detail(Long gradeId);

    /**
     * 等级分页查询
     * @param query 查询条件
     * @return 等级集合
     */
    List<DistributorGradeBusiDTO> findPage(DistributorGradeQuery query);

    /**
     * 更新等级时查询可用的上级
     * @param systemId 体系ID
     * @param gradeId 等级ID
     * @return 等级集合
     */
    List<DistributorGradeBusiDTO> findParentNodesForUpdate(Long systemId,Long gradeId);

    /**
     * 新增等级时查询可用的上级
     * @param systemId 体系ID
     * @return 等级集合
     */
    List<DistributorGradeBusiDTO> findParentNodesForCreate(Long systemId);

    /**
     * 根据体系查询所有的等级
     * @param systemId 体系ID
     * @return 等级集合
     */
    List<DistributorGradeBusiDTO> findAllGradesBySystem(long systemId);

    /**
     * 批量删除等级
     * @param idList 等级ID集合
     * @param forceDelete 是否强制删除 0否 1是
     * @return
     */
    Boolean deleteBatchByIds(List<Long> idList,Integer forceDelete);

    /**
     * 校验等级是否有经销商挂载
     * @param gradeIdList 等级ID集合
     */
    void validateHasDistributors(List<Long> gradeIdList);

    /**
     * 校验等级是否有下级
     * @param gradeIdList 等级ID集合
     */
    void validateHasChildren(List<Long> gradeIdList);

    /**
     * 删除与经销商的关联关系
     * @param gradeIdList 等级ID集合
     * @return 是否成功
     */
    Boolean deleteDistributors(List<Long> gradeIdList);

    /**
     * 创建等级
     * @param dto 新增实体
     * @return 新增记录的ID
     */
    Long create(DistributorGradeBusiDTO dto);

    /**
     * 更新等级
     * @param dto 更新实体
     * @return 是否成功
     */
    Boolean update(DistributorGradeBusiDTO dto);

    /**
     * 获取所有下级
     * @param id 等级ID
     * @return 等级集合
     */
    List<DistributorGradeBusiDTO> listChildrenNodes(Long id);

    /**
     * 校验等级编码是否重复
     * @param gradeCode 新增的编码
     * @param systemId 体系ID
     */
    void validateDistributorGradeCode(String gradeCode,Long systemId);

    /**
     *校验中文名称是否重复
     * @param gradeName 新增的等级中文名称
     * @param systemId 体系ID
     */
    void validateDistributorGradeName(String gradeName,Long systemId);

    /**
     * 校验英文名称是否重复
     * @param gradeNameEn 新增的英文名称
     * @param systemId 体系ID
     */
    void validateDistributorGradeNameEn(String gradeNameEn,Long systemId);



}