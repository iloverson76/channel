package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcAreaType;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_area_type
* @author admin
*/
public class CcAreaTypeDto extends CcAreaType{

public CcAreaTypeDto(){};

public CcAreaTypeDto(CcAreaType eo) {
    this.setId(eo.getId());
    this.setParentId(eo.getParentId());
    this.setLimitParent(eo.getLimitParent());
    this.setAreaTypeName(eo.getAreaTypeName());
    this.setAreaTypeCode(eo.getAreaTypeCode());
    this.setAreaTypeNameEn(eo.getAreaTypeNameEn());
    this.setPath(eo.getPath());
    this.setDescription(eo.getDescription());
    this.setVersion(eo.getVersion());
    this.setRemark(eo.getRemark());
    this.setDr(eo.getDr());
    this.setCreatedBy(eo.getCreatedBy());
    this.setCreatedTime(eo.getCreatedTime());
    this.setUpdatedBy(eo.getUpdatedBy());
    this.setUpdatedTime(eo.getUpdatedTime());
    this.setAppId(eo.getAppId());
    this.setTenantId(eo.getTenantId());
}
}

