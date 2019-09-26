package com.deepexi.channel.service;


import com.deepexi.channel.domain.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface AreaBusinessService {

  /**
   * 创建区域
   * @param dto 新增的区域实体
   * @return 新纪录ID
   */
   long create(AreaBusiDTO dto);

  /**
   * 区域列表查询
   * @param query 查询条件
   * @return 区域集合
   */
   List<AreaBusiDTO> findPage(AreaQuery query);

    /**
     * 获取区域详情
     * @param pk 区域ID
     * @param areaTypeId 区域分类ID
     * @return 区域实体
     */
    AreaBusiDTO detail(Long pk, Long areaTypeId);

    /**
     * 构建区域树
     * @param query 查询条件
     * @return 区域树集合
     */
    List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query);

    /**
     * 获取子区域树
     * @param areaId 区域ID
     * @return 子区域树集合
     */
    List<AreaTreeDTO> listChildrenTree(Long areaId);

    /**
     * 根据区域类型获取所有挂载的区域
     * @param areaTypeId 区域类型ID
     * @return 区域实体集合
     */
    List<AreaBusiDTO> listLinkedAreasByType(Long areaTypeId);

    /**
     * 批量删除区域
     * @param ids 区域ID集合
     * @param forDelete 是否强制删除
     * @return 是否成功
     */
    boolean deleteBatchByIds(List<Long> ids,Integer forDelete);

    /**
     * 校验区域是否有子区域
     * @param idList
     */
    void validateHasChildren(List<Long> idList);

    /**
     * 校验区域是否有经销商关联
     * @param idList 区域ID集合
     */
    void validateHasDistributors(List<Long> idList);

    /**
     * 校验区域是否有门店关联
     * @param idList 区域ID集合
     */
    void validateHasStores(List<Long> idList);

    /**
     * 校验区域是否挂载在树上
     * @param idList 区域ID集合
     */
    void validateAreaOnTrea(List<Long> idList);

    /**
     * 校验区域编码是否重复
     * @param areaCode 新增的区域编码
     */
    void validateAreaCode(String areaCode);

    /**
     * 校验区域中文名是否重复
     * @param areaName 新增时的区域编码
     */
    void validateAreaName(String areaName);

    /**
     * 校验区域英文名是否重复
     * @param areaNameEn 新增时的区域英文名
     */
    void validateAreaNameEn(String areaNameEn);

    /**
     * 删除所有子区域
     * @param idList 区域ID集合
     * @return 是否成功
     */
    boolean deleteChildren(List<Long> idList);

    /**
     * 删除所有与区域关联的经销商
     * @param idList 区域ID集合
     * @return 是否成功
     */
    boolean deleteDistributors(List<Long> idList);

    /**
     * 删除所有与区域关联的门店
     * @param idList 区域ID集合
     * @return 是否成功
     */
    boolean deleteStores(List<Long> idList);

    /**
     * 更新区域
     * @param dto 更新实体
     * @return 是否成功
     */
    boolean update(AreaBusiDTO dto);

    /**
     * 修改树节点为根节点
     * @param areaId 区域ID
     * @return 是否成功
     */
    boolean updateToRootNode(Long areaId);

    /**
     * 增加区域树节点
     * @param dto 区域实体
     * @return 是否成功
     */
    boolean treeAddNode(AreaDTO dto);

    /**
     * 更新区域树节点
     * @param dto 更新区域实体
     * @return 是否成功
     */
    boolean treeUpdateNode(AreaDTO dto);

    /**
     * 删除区域树节点
     * @param pk 区域ID
     * @return 是否成功
     */
    boolean treeDeleteNode(Long pk);
}