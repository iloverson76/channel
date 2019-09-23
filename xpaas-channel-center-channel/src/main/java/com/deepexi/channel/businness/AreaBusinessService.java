package com.deepexi.channel.businness;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTreeDTO;
import com.deepexi.channel.domain.area.AreaTreeQuery;

import java.util.List;

/**
 * 区域聚合层接口
 */
public interface AreaBusinessService {

   long create(AreaDTO dto);

   List<AreaDTO> findPage(AreaQuery query);

    AreaDTO detail(Long pk, Long areaTypeId);

    List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query);

    List<AreaTreeDTO> listChildrenTree(Long areaId);

    List<AreaDTO> listLinkedAreasByType(Long areaTypeId);

 boolean deleteBatchByIds(List<Long> ids,Integer forDelete);

    void validateHasChildren(List<Long> idList);

    void validateHasDistributors(List<Long> idList);

    void validateHasStores(List<Long> idList);

    boolean deleteChildren(List<Long> idList);

    boolean deleteDistributors(List<Long> idList);

    boolean deleteStores(List<Long> idList);

    boolean update(AreaDTO dto);

    boolean updateToRootNode(Long areaId);

    boolean treeAddNode(AreaDTO clone);

    boolean treeUpdateNode(AreaDTO clone);

    boolean treeDeleteNode(Long pk);
}