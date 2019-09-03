package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcArea;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_area
* @author admin
*/
public class CcAreaDto extends CcArea{

public CcAreaDto(){};

public CcAreaDto(CcArea eo) {
    this.setDr(eo.getDr());
    this.setCreatedBy(eo.getCreatedBy());
    this.setCreatedTime(eo.getCreatedTime());
    this.setUpdatedBy(eo.getUpdatedBy());
    this.setUpdatedTime(eo.getUpdatedTime());
    this.setAppId(eo.getAppId());
    this.setTenantId(eo.getTenantId());
    this.setId(eo.getId());
    this.setParentId(eo.getParentId());
    this.setAreaTypeId(eo.getAreaTypeId());
    this.setAreaName(eo.getAreaName());
    this.setAreaCode(eo.getAreaCode());
    this.setAreaNameEn(eo.getAreaNameEn());
    this.setPath(eo.getPath());
    this.setDescription(eo.getDescription());
    this.setVersion(eo.getVersion());
    this.setRemark(eo.getRemark());
}
}

