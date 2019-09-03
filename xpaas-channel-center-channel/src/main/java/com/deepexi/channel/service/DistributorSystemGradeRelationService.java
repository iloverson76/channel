package com.deepexi.channel.service;

import com.deepexi.channel.domain.distributor.DistributorSystemGradeRelationDTO;
import com.deepexi.channel.domain.distributor.DistributorGradeSystemQuery;
import com.deepexi.channel.domain.distributor.DistributorSystemGradeRelationDTO;

import java.util.List;
import java.util.Set;

/**
 * cc_distributor_grade_system
 */
public interface DistributorSystemGradeRelationService {

    /**
    * 创建
    * @return
    */
    long create(DistributorSystemGradeRelationDTO dto);

    /**
     更新
     */
    Boolean update(DistributorSystemGradeRelationDTO dto);

    /**
     批量删除
    */
    Boolean delete(List<Long> pkList);

    /**
     批量删除
     */
    Boolean deleteByGradeIds(List<Long> gradeIdList);

    /**
     批量删除
     */
    Boolean deleteBySystemIds(List<Long> SystemIdList);

    /**
     删除
     */
    Boolean deleteByGradeIdAndSystemId(Long gradeId,Long SystemId);

    /**
     获取详情
     */
    DistributorSystemGradeRelationDTO detail(Long pk);

    /**
     * 详情
     * @param gradeId
     * @param SystemId
     * @return
     */
    DistributorSystemGradeRelationDTO detailByGradeIdAndSystemId(Long gradeId,Long SystemId);

    /**
     * 分页查询
     */
    List<DistributorSystemGradeRelationDTO> listPageByIds(List<Long> pkOrGradeIdOrSystemIdList);
}