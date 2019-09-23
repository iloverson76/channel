package com.deepexi.channel.service;


import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaQuery;

import java.util.List;

/**
 * cc_area
 */
public interface AreaService {

    Long create(AreaDTO dto);

    boolean update(AreaDTO dto);

    List<AreaDTO> findPage(AreaQuery query);

    AreaDTO getAreaById(Long pk);

    boolean deleteBatch(List<Long> ids);

    boolean deleteById(Long id);

    List<AreaDTO> listChildrenAreas(Long areaId);

    List<AreaDTO> listLinkedAreasByType(Long areaTypeId);

    boolean updateBatch(List<AreaDTO> dtoList);

    List<AreaDTO> findAllByIds(List<Long> ids);

    List<AreaDTO> findTree();
}