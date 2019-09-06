package com.deepexi.channel.service;

import com.deepexi.channel.domain.area.AreaDTO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import com.deepexi.channel.domain.eo.CcArea;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_area
 */
public interface AreaService {

    Long create(AreaDTO dto);

    boolean update(AreaDTO dto);

    List<AreaDTO> findPage(AreaQuery query);

}