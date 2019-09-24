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
    Boolean deleteBatchByIds(List<Long> ids);

    /**
     * 单条删除经销商等级
     * @param id
     * @return
     */
    Boolean deleteById(Long id);

    /**
     * 分页获取列表
     */
    List<DistributorGradeDTO> findPage(DistributorGradeQuery query);

    /**
     * 查找某个体系下的所有等级
     */
    List<DistributorGradeDTO> findAllBySystem(Long systemId);

    boolean updateBatchById(List<DistributorGradeDTO> dtoList);

    List<String> listDistributorGradeCode(Long systemId);

    List<String> listDistributorGradeName(Long systemId);

    List<String> listDistributorGradeNameEn(Long systemId);
}