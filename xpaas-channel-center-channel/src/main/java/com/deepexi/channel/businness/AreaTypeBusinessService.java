package com.deepexi.channel.businness;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.util.CollectionUtil;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface AreaTypeBusinessService {

   List<AreaDTO> listLinkedAreas(long pk);

   List<AreaTypeDTO> findPage(AreaTypeQuery query);

    List<AreaTypeDTO> getListAreaType(List<Long> ids);

    List<AreaTypeDTO> findParentAreaTypeByAreaId(Long areaId);

    boolean deleteAreaTypeByIds(List<Long> idList,Integer forceDelete);

    boolean update(AreaTypeDTO dto);

    List<AreaTypeDTO> listParentNodesForUpdate(Long id);

    List<AreaTypeDTO> listParentNodesForCreate();

    boolean deleteAreaTypeById(Long id);

    List<Long> validateHasAreas(List<Long> idList);

    List<Long> validateHasChildren(List<Long> idList);

    Long createAreaType(AreaTypeDTO dto);
}