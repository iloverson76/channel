package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcStoreType;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_store_type
* @author admin
*/
public class CcStoreTypeDto extends CcStoreType{

public CcStoreTypeDto(){};

public CcStoreTypeDto(CcStoreType eo) {
    this.setId(eo.getId());
    this.setStoreTypeName(eo.getStoreTypeName());
    this.setStoreTypeCode(eo.getStoreTypeCode());
    this.setStoreTypeNameEn(eo.getStoreTypeNameEn());
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

