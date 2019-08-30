package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcStoreDistributorRelation;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_store_distributor_relation
* @author admin
*/
public class CcStoreDistributorRelationDto extends CcStoreDistributorRelation{

public CcStoreDistributorRelationDto(){};

public CcStoreDistributorRelationDto(CcStoreDistributorRelation eo) {
    this.setId(eo.getId());
    this.setStoreId(eo.getStoreId());
    this.setDistributorId(eo.getDistributorId());
    this.setGradeSystemId(eo.getGradeSystemId());
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

