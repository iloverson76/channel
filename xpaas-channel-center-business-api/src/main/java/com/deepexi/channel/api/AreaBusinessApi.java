package com.deepexi.channel.api;


import com.deepexi.channel.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface AreaBusinessApi {

  /**
   * 创建区域
   * @param dto 新增的区域实体
   * @return 新纪录ID
   */
  @PostMapping("/areaBusi")
   long create(@RequestBody AreaBusiDTO dto);

  /**
   * 区域列表查询
   * @param query 查询条件
   * @return 区域集合
   */
  @GetMapping("/areaBusi/page")
   List<AreaBusiDTO> findPage(@RequestBody AreaQuery query);

    /**
     * 获取区域详情
     * @param pk 区域ID
     * @param areaTypeId 区域分类ID
     * @return 区域实体
     */
    @GetMapping("/areaBusi/{id}/{areaTypeId}")
    AreaBusiDTO detail(@PathVariable(value = "id") Long pk, @PathVariable(value = "areaTypeId") Long areaTypeId);

    /**
     * 构建区域树
     * @param query 查询条件
     * @return 区域树集合
     */
    @GetMapping("/areaBusi/buildAreaTree")
    List<AreaTreeDTO> buildAreaTree(@RequestBody AreaTreeQuery query);

    /**
     * 获取子区域树
     * @param areaId 区域ID
     * @return 子区域树集合
     */
    @GetMapping("/areaBusi/listChildrenTree/{areaId}")
    List<AreaTreeDTO> listChildrenTree(@PathVariable(value = "areaId") Long areaId);

    /**
     * 根据区域类型获取所有挂载的区域
     * @param areaTypeId 区域类型ID
     * @return 区域实体集合
     */
    @GetMapping("/areaBusi/listLinkedAreasByType/{areaTypeId}")
    List<AreaBusiDTO> listLinkedAreasByType(@PathVariable(value = "areaTypeId") Long areaTypeId);

    /**
     * 批量删除区域
     * @param ids 区域ID集合
     * @param forceDelete 是否强制删除
     * @return 是否成功
     */
    @DeleteMapping("/areaBusi/deleteBatch")
    boolean deleteBatchByIds(@RequestBody List<Long> ids, @PathVariable(value = "forceDelete") Integer forceDelete);

    /**
     * 校验区域是否有子区域
     * @param idList
     */
    @GetMapping("/areaBusi/validateHasChildren")
    void validateHasChildren(@RequestBody List<Long> idList);

    /**
     * 校验区域是否有经销商关联
     * @param idList 区域ID集合
     */
    @GetMapping("/areaBusi/validateHasDistributors")
    void validateHasDistributors(@RequestBody List<Long> idList);

    /**
     * 校验区域是否有门店关联
     * @param idList 区域ID集合
     */
    @GetMapping("/areaBusi/validateHasStores")
    void validateHasStores(@RequestBody List<Long> idList);

    /**
     * 校验区域是否挂载在树上
     * @param idList 区域ID集合
     */
    @GetMapping("/areaBusi/validateAreaOnTrea")
    void validateAreaOnTrea(@RequestBody List<Long> idList);

    /**
     * 校验区域编码是否重复
     * @param areaCode 新增的区域编码
     */
    @GetMapping("/areaBusi/validateAreaCode/{areaCode}")
    void validateAreaCode(@PathVariable(value="areaCode") String areaCode);

    /**
     * 校验区域中文名是否重复
     * @param areaName 新增时的区域编码
     */
    @GetMapping("/areaBusi/validateAreaName/{areaName}")
    void validateAreaName(@PathVariable(value = "areaName") String areaName);

    /**
     * 校验区域英文名是否重复
     * @param areaNameEn 新增时的区域英文名
     */
    @GetMapping("/areaBusi/validateAreaNameEn/{areaNameEn}")
    void validateAreaNameEn(@PathVariable(value = "areaNameEn") String areaNameEn);

    /**
     * 删除所有子区域
     * @param idList 区域ID集合
     * @return 是否成功
     */
    @DeleteMapping("/areaBusi/deleteChildren")
    boolean deleteChildren(@RequestBody List<Long> idList);

    /**
     * 删除所有与区域关联的经销商
     * @param idList 区域ID集合
     * @return 是否成功
     */
    @DeleteMapping("/areaBusi/deleteDistributors")
    boolean deleteDistributors(@RequestBody List<Long> idList);

    /**
     * 删除所有与区域关联的门店
     * @param idList 区域ID集合
     * @return 是否成功
     */
    @DeleteMapping("/areaBusi/deleteStores")
    boolean deleteStores(@RequestBody List<Long> idList);

    /**
     * 更新区域
     * @param id 区域ID
     * @param dto 更新实体
     * @return 是否成功
     */
    @PutMapping("/areaBusi/{id}")
    boolean update(@PathVariable(value = "id") Long id, @RequestBody AreaBusiDTO dto);

    /**
     * 修改树节点为根节点
     * @param areaId 区域ID
     * @return 是否成功
     */
    @PutMapping("/areaBusi/updateToRootNode/{areaId}")
    boolean updateToRootNode(@PathVariable(value = "areaId") Long areaId);

    /**
     * 增加区域树节点
     * @param dto 区域实体
     * @return 是否成功
     */
    @PostMapping("/areaBusi/treeAddNode")
    boolean treeAddNode(@RequestBody AreaDTO dto);

    /**
     * 更新区域树节点
     * @param dto 更新区域实体
     * @return 是否成功
     */
    @PutMapping("/areaBusi/treeUpdateNode")
    boolean treeUpdateNode(@RequestBody AreaDTO dto);

    /**
     * 删除区域树节点
     * @param pk 区域ID
     * @return 是否成功
     */
    @DeleteMapping("/areaBusi/treeDeleteNode/{id}")
    boolean treeDeleteNode(@PathVariable(value = "id") Long pk);
}