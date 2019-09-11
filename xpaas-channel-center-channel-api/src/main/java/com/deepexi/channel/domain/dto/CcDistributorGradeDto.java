package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcDistributorGrade;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_distributor_grade
* @author admin
*/
public class CcDistributorGradeDto extends CcDistributorGrade{

public CcDistributorGradeDto(){};

public CcDistributorGradeDto(CcDistributorGrade eo) {
    this.setId(eo.getId());
    this.setParentId(eo.getParentId());
    this.setRoot(eo.getRoot());
    this.setDistributorGradeName(eo.getDistributorGradeName());
    this.setDistributorGradeCode(eo.getDistributorGradeCode());
    this.setGradeSystemId(eo.getGradeSystemId());
    this.setVersion(eo.getVersion());
    this.setRemark(eo.getRemark());
    this.setDescription(eo.getDescription());
    this.setDr(eo.getDr());
    this.setCreatedBy(eo.getCreatedBy());
    this.setCreatedTime(eo.getCreatedTime());
    this.setUpdatedBy(eo.getUpdatedBy());
    this.setUpdatedTime(eo.getUpdatedTime());
    this.setAppId(eo.getAppId());
    this.setTenantId(eo.getTenantId());
}
}

