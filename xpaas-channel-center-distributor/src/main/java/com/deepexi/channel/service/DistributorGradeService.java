package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorGradeDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorGradeService {

    /**
     * 创建经销商等级
     * @param dto 新增实体
     * @return 新增记录ID
     */
    Long create(DistributorGradeDTO dto);

    /**
     * 根据ID获取等级
     * @param pk 主键
     * @return 等级
     */
    DistributorGradeDTO getById(Long pk);

    /**
     * 更新经销商等级
     * @param dto 更新实体
     * @return 是否成功
     */
    Boolean updateById(DistributorGradeDTO dto);

    /**
     * 批量删除经销商等级
     * @param ids ID集合
     * @return 是否成功
     */
    Boolean deleteBatchByIds(List<Long> ids);

    /**
     * 删除经销商等级
     * @param id 主键
     * @return 是否成功
     */
    Boolean deleteById(Long id);

    /**
     * 分页查询等级列表
     * @param query 查询条件
     * @return 等级集合
     */
    List<DistributorGradeDTO> findPage(DistributorGradeQuery query);

    /**
     * 根据体系ID查找所有的挂载的等级
     * @param systemId 体系ID
     * @return 等级集合
     */
    List<DistributorGradeDTO> findAllBySystem(Long systemId);

    /**
     * 批量更新等级
     * @param dtoList 等级集合
     * @return 是否成功
     */
    boolean updateBatchById(List<DistributorGradeDTO> dtoList);

    /**
     * 根据体系ID查找所有挂载的等级编码
     * @param systemId 体系ID
     * @return 等级编码集合
     */
    List<String> listDistributorGradeCode(Long systemId);

    /**
     * 根据体系ID查找所有的等级名称
     * @param systemId 体系ID
     * @return 等级名称集合
     */
    List<String> listDistributorGradeName(Long systemId);

    /**
     * 根据体系ID查找所有的等级英文名称
     * @param systemId 体系ID
     * @return 等级英文名集合
     */
    List<String> listDistributorGradeNameEn(Long systemId);
}