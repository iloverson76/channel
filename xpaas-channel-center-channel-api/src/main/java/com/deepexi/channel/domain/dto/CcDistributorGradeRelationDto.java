package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcDistributorGradeRelation;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_distributor_grade_relation
* @author admin
*/
public class CcDistributorGradeRelationDto extends CcDistributorGradeRelation{

public CcDistributorGradeRelationDto(){};

public CcDistributorGradeRelationDto(CcDistributorGradeRelation eo) {
    this.setId(eo.getId());
    this.setDistributorId(eo.getDistributorId());
    this.setDistributorGradeId(eo.getDistributorGradeId());
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

