package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;

import java.util.List;

/**
 * cc_distributor_grade_system
 */
public interface DistributorGradeSystemService {

    /**
    * 创建
    * @return
    */
    long create(DistributorGradeSystemDTO dto);

    /**
     更新
     */
    Boolean update(DistributorGradeSystemDTO dto);

    /**
     批量删除
    */
    Boolean delete(List<Long> idList);

    /**
     获取详情
     */
    DistributorGradeSystemDTO detail(Long pk);

    /**
     * 分页获取列表
     */
    List<DistributorGradeSystemDTO> findPage(DistributorGradeSystemQuery query);

    /**
     * 校验重复编码和名称
     */
    void validateDuplicatedNameAndCode(DistributorGradeSystemDTO dto);

}