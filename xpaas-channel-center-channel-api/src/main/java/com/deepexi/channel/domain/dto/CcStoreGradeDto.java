package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcStoreGrade;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_store_grade
* @author admin
*/
public class CcStoreGradeDto extends CcStoreGrade{

public CcStoreGradeDto(){};

public CcStoreGradeDto(CcStoreGrade eo) {
    this.setId(eo.getId());
    this.setStoreGradeName(eo.getStoreGradeName());
    this.setStoreGradeCode(eo.getStoreGradeCode());
    this.setStoreGradeNameEn(eo.getStoreGradeNameEn());
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

