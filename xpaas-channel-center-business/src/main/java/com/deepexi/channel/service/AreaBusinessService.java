package com.deepexi.channel.service;


import com.deepexi.channel.domain.*;

import java.util.List;

/**
 * 区域聚合层接口
 */
public interface AreaBusinessService {

   long create(AreaBusiDTO dto);

   List<AreaBusiDTO> findPage(AreaQuery query);

    AreaBusiDTO detail(Long pk, Long areaTypeId);

    List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query);

    List<AreaTreeDTO> listChildrenTree(Long areaId);

    List<AreaBusiDTO> listLinkedAreasByType(Long areaTypeId);

    boolean deleteBatchByIds(List<Long> ids,Integer forDelete);

    void validateHasChildren(List<Long> idList);

    void validateHasDistributors(List<Long> idList);

    void validateHasStores(List<Long> idList);

    void validateAreaOnTrea(List<Long> idList);

    void validateAreaCode(String areaCode);

    void validateAreaName(String areaName);

    void validateAreaNameEn(String areaNameEn);

    boolean deleteChildren(List<Long> idList);

    boolean deleteDistributors(List<Long> idList);

    boolean deleteStores(List<Long> idList);

    boolean update(AreaBusiDTO dto);

    boolean updateToRootNode(Long areaId);

    boolean treeAddNode(AreaDTO clone);

    boolean treeUpdateNode(AreaDTO clone);

    boolean treeDeleteNode(Long pk);
}