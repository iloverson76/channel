package com.deepexi.channel.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deepexi.channel.domain.area.AreaTypeDO;
import com.deepexi.channel.domain.area.AreaTypeQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface IAreaTypeDAO extends  IService<AreaTypeDO> {

    int removeAreaTypeByIds(Set<Long> ids);

    List<AreaTypeDO> listAreaTypePage(AreaTypeQuery query);

    List<String> listAreaTypeCode(String tenantId,String appId);

    List<AreaTypeDO> listChidren(String tenantId, String appId,Long id);

    List<AreaTypeDO> listNodeWithoutChildren(String tenantId, String appId);

    List<AreaTypeDO> listParentForCreate(String tenantId, String appId);

    AreaTypeDO getChildNode( String tenantId,  String appId, Long id);

    List<AreaTypeDO> listNotLimitedNode(String tenantId, String appId);

}
