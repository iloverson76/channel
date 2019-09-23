package com.deepexi.channel.service;


import com.deepexi.channel.domain.AreaDTO;
import com.deepexi.channel.domain.AreaTypeDTO;
import com.deepexi.channel.domain.AreaTypeQuery;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface AreaTypeBusinessService {

   List<AreaDTO> listLinkedAreas(long pk);

   List<AreaTypeDTO> findPage(AreaTypeQuery query);

    List<AreaTypeDTO> getListAreaType(List<Long> ids);

    List<AreaTypeDTO> findParentAreaTypeByAreaId(Long areaId);

    boolean deleteAreaTypeByIds(List<Long> idList);

    boolean update(AreaTypeDTO dto);

    List<AreaTypeDTO> listParentNodesForUpdate(Long id);

    List<AreaTypeDTO> listParentNodesForCreate();

    boolean deleteAreaTypeById(Long id);
}