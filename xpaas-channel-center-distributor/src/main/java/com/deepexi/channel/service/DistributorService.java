package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorDTO;
import com.deepexi.channel.domain.DistributorQuery;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorService {

    /**
     * 创建经销商
     * @param dto 新增实体
     * @return 新增记录ID
     */
    long create(DistributorDTO dto);

    /**
     * 批量删除经销商
     * @param idList 实体ID集合
     * @return 是否成功
     */
    boolean deleteBatch(List<Long> idList);

    /**
     * 经销商分页查询
     * @param query 查询条件
     * @return 经销商实体集合
     */
    List<DistributorDTO> findPage(DistributorQuery query);

    /**
     * 更新经销商
     * @param dto 更新实体
     * @return 是否成功
     */
    boolean update(DistributorDTO dto);

    /**
     * 获取经销商
     * @param id 主键
     * @return 经销商实体
     */
    DistributorDTO getById(Long id);

    /**
     * 获取所有的经销商编码
     * @return 编码集合
     */
    List<String> listDistributorCode();

    /**
     * 获取所有的经销商中文名
     * @return 中文名集合
     */
    List<String> listDistributorName();

    /**
     * 获取所有的经销商英文名
     * @return 英文名集合
     */
    List<String> listDistributorNameEn();
}