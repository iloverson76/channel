package com.deepexi.channel.businness;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.domain.chain.ChainTypeDTO;

import java.util.List;
import java.util.Set;

/**
 * cc_distributor_grade
 */
public interface AreaTypeBusinessService {

   List<AreaDTO> listLinkedAreas(long pk);

   List<AreaTypeDTO> findPage(AreaTypeQuery query);

    List<AreaTypeDTO> getListAreaType(List<Long> ids);

    List<AreaTypeDTO> findParentAreaTypeByAreaId(Long areaId);

    boolean deleteAreaTypeByIds(List<Long> idList);
}