package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcStoreGradeRelation;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_store_grade_relation
* @author admin
*/
public class CcStoreGradeRelationDto extends CcStoreGradeRelation{

public CcStoreGradeRelationDto(){};

public CcStoreGradeRelationDto(CcStoreGradeRelation eo) {
    this.setId(eo.getId());
    this.setStoreId(eo.getStoreId());
    this.setStoreGradeId(eo.getStoreGradeId());
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

