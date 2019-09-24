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
     * 查询单条记录
     */
    DistributorGradeSystemDTO getById(Long id);

    /**
     * 分页获取列表
     */
    List<DistributorGradeSystemDTO> findPage(DistributorGradeSystemQuery query);

    List<String> listGradeSystemCode();

    List<String> listGradeSystemName();

    List<String> listGradeSystemNameEn();

}