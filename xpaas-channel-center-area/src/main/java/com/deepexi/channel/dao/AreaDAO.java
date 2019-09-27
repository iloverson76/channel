package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.AreaDO;
import com.deepexi.channel.domain.AreaQuery;

import java.util.List;

public interface AreaDAO extends  IService<AreaDO> {

    List<AreaDO> listAreaPage(AreaQuery query);

    List<AreaDO> listChildrenAreas(Long areaId);

    List<AreaDO> listLinkedAreasByType(Long areaTypeId);

    List<AreaDO> findAllByIds(List<Long> ids);

    List<AreaDO> findTree(Integer level);

    List<String> listAreaCode();

    List<String> listAreaName();

    List<String> listAreaNameEn();
}
