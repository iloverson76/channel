package com.deepexi.channel.businness;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTreeDTO;
import com.deepexi.channel.domain.area.AreaTreeQuery;
import com.deepexi.channel.domain.distributor.DistributorDTO;

import java.util.List;

/**
 * cc_distributor_grade
 */
public interface AreaBusinessService {

   long create(AreaDTO dto);

   List<AreaDTO> findPage(AreaQuery query);

    AreaDTO detail(Long pk, Long areaTypeId);

    List<AreaTreeDTO> buildAreaTree(AreaTreeQuery query);
}