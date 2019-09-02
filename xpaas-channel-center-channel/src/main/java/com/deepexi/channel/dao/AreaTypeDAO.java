package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeQuery;

import java.util.List;

public interface AreaTypeDAO extends  IService<AreaTypeDO> {

    List<AreaTypeDO> listAreaTypePage(AreaTypeQuery query);

    List<String> listAreaTypeCode(String tenantId, String appId);

    AreaTypeDO getChildNode(String tenantId, String appId, Long id);

    List<AreaTypeDO> listNotLimitedNode(String tenantId, String appId);

    List<AreaTypeDO> listChildNodes(String tenantId, String appId,String idPath);

}
