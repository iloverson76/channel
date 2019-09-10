package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.area.AreaDO;
import com.deepexi.channel.domain.area.AreaQuery;
import com.deepexi.channel.domain.area.AreaTypeQuery;

import java.util.List;

public interface AreaDAO extends  IService<AreaDO> {

    List<AreaDO> listAreaPage(AreaQuery query);

    List<AreaDO> listChildrenAreas(Long areaId);

    List<AreaDO> listLinkedAreasByType(Long areaTypeId);
}
