package com.deepexi.channel.service;


import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaQuery;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface AreaService {

    /**
     * 创建区域
     * @param dto 新增的区域实体
     * @return 新纪录ID
     */
    Long create(AreaDTO dto);

    /**
     * 更新区域
     * @param dto 更新的区域实体
     * @return 是否成功
     */
    boolean update(AreaDTO dto);

    /**
     * 区域列表查询
     * @param query 查询条件
     * @return 区域集合
     */
    List<AreaDTO> findPage(AreaQuery query);

    /**
     * 根据ID获取区域实体
     * @param pk 主键
     * @return 区域
     */
    AreaDTO getAreaById(Long pk);

    /**
     *批量删除区域实体
     * @param ids 区域ID集合
     * @return 是否成功
     */
    boolean deleteBatch(List<Long> ids);

    /**
     * 单次删除区域实体
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据区域ID获取子区域
     * @param areaId 区域ID
     * @return 区域集合
     */
    List<AreaDTO> listChildrenAreas(Long areaId);

    /**
     * 根据区域分类ID获取区域挂载的所有区域
     * @param areaTypeId 区域分类ID
     * @return 区域集合
     */
    List<AreaDTO> listLinkedAreasByType(Long areaTypeId);

    /**
     * 批量更新区域实体
     * @param dtoList 区域实体列表
     * @return 是否成功
     */
    boolean updateBatch(List<AreaDTO> dtoList);

    /**
     * 根据ID集合获取所有区域实体
     * @param ids 区域ID集合
     * @return 区域集合
     */
    List<AreaDTO> findAllByIds(List<Long> ids);

    /**
     * 查找区域区域树
     * @param level 展开层级
     * @return 区域树集合
     */
    List<AreaDTO> findTree(Integer level);

    /**
     * 获取所有的区域编码
     * @return 区域编码集合
     */
    List<String> listAreaCode();

    /**
     * 获取所有的区域名称
     * @return 区域中文名集合
     */
    List<String> listAreaName();

    /**
     * 获取所有的区域英文名称
     * @return 区域英文名集合
     */
    List<String> listAreaNameEn();
}