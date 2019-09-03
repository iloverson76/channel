package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcStoreTypeRelation;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_store_type_relation
* @author admin
*/
public class CcStoreTypeRelationDto extends CcStoreTypeRelation{

public CcStoreTypeRelationDto(){};

public CcStoreTypeRelationDto(CcStoreTypeRelation eo) {
    this.setId(eo.getId());
    this.setStoreId(eo.getStoreId());
    this.setStoreTypeId(eo.getStoreTypeId());
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

