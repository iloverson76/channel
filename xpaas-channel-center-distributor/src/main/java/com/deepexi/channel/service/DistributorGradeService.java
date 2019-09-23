package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorGradeDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface DistributorGradeService {

    /**
     * 创建经销商等级
     */
    Long create(DistributorGradeDTO dto);
    /**
     *根据ID获取
    */
    DistributorGradeDTO getById(Long pk);

    /**
     更新经销商等级
    */
    Boolean updateById(DistributorGradeDTO dto);

    /**
     批量删除经销商等级
    */
    Boolean delete(List<Long> ids);

    /**
     * 分页获取列表
     */
    List<DistributorGradeDTO> findPage(DistributorGradeQuery query);

    boolean validateGradeCode(String garedCode);

    /**
     * 查找某个体系下的所有等级
     */
    List<DistributorGradeDTO> findAllBySystem(Long systemId);

    List<DistributorGradeDTO> listChildrenNodes(Long id);

    boolean updateBatchById(List<DistributorGradeDTO> dtoList);


}