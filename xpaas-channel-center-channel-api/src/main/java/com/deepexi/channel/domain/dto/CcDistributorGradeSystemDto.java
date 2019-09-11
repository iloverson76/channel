package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcDistributorGradeSystem;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_distributor_grade_system
* @author admin
*/
public class CcDistributorGradeSystemDto extends CcDistributorGradeSystem{

public CcDistributorGradeSystemDto(){};

public CcDistributorGradeSystemDto(CcDistributorGradeSystem eo) {
    this.setId(eo.getId());
    this.setGradeSystemName(eo.getGradeSystemName());
    this.setGradeSystemCode(eo.getGradeSystemCode());
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

