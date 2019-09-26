package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorGradeSystemService {

    /**
     * 创建经销商体系
     * @param dto 新增实体
     * @return 新增记录ID
     */
    long create(DistributorGradeSystemDTO dto);

    /**
     * 更新体系
     * @param dto 更新实体
     * @return 是否成功
     */
    Boolean update(DistributorGradeSystemDTO dto);

    /**
     * 批量删除体系
     * @param idList 体系ID集合
     * @return 是否成功
     */
    Boolean deleteBatchByIds(List<Long> idList);

    /**
     * 查询体系详情
      * @param pk 体系ID
     * @return 体系实体详情
     */
    DistributorGradeSystemDTO detail(Long pk);

    /**
     * 获取体系
     * @param id 体系ID
     * @return 体系实体
     */
    DistributorGradeSystemDTO getById(Long id);

    /**
     * 体系分页查询
     * @param query 查询条件
     * @return 体系实体集合
     */
    List<DistributorGradeSystemDTO> findPage(DistributorGradeSystemQuery query);

    /**
     * 获取所有体系编码
     * @return 体系编码集合
     */
    List<String> listGradeSystemCode();

    /**
     * 获取所有体系名称
     * @return 体系名称集合
     */
    List<String> listGradeSystemName();

    /**
     * 获取所有体系英文名
     * @return 体系英文名集合
     */
    List<String> listGradeSystemNameEn();

}