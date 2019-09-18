package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeDTO;
import com.deepexi.channel.domain.area.AreaTypeQuery;

import java.util.List;

public interface AreaTypeDAO extends  IService<AreaTypeDO> {

    List<AreaTypeDO> listAreaTypePage(AreaTypeQuery query);

    List<String> listAreaTypeCode(String tenantId, String appId);

    List<AreaTypeDO> listChildNodes(String idPath);

    List<AreaTypeDTO> listAreaTypeByIds(List<Long> areaTyeIdList);

    List<AreaTypeDO> listLinkedAreas(long pk);

    List<AreaTypeDO> findByAreaIdNotInLinkIdAll(List<Long> linkIdList);

    boolean update(AreaTypeDO tdo);
}
