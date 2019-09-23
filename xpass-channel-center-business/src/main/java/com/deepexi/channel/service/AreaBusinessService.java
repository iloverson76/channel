package com.deepexi.channel.service;


import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaQuery;
import com.deepexi.channel.domain.AreaTreeDTO;
import com.deepexi.channel.domain.AreaTreeQuery;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface AreaBusinessService {

   long create(AreaDTO dto);

   List<AreaDTO> findPage(AreaQuery query);

    AreaDTO detail(Long pk, Long areaTypeId);

    List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query);

    List<AreaTreeDTO> listChildrenTree(Long areaId);

    List<AreaDTO> listLinkedAreasByType(Long areaTypeId);

    boolean deleteBatch(List<Long> ids);

    boolean deleteById(Long id);

    boolean update(AreaDTO dto);

    boolean updateToRootNode(Long areaId);

    boolean treeAddNode(AreaDTO clone);

    boolean treeUpdateNode(AreaDTO clone);

    boolean treeDeleteNode(Long pk);
}