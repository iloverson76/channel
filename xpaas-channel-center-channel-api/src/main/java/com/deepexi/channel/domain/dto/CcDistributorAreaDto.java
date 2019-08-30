package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcDistributorArea;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_distributor_area
* @author admin
*/
public class CcDistributorAreaDto extends CcDistributorArea{

public CcDistributorAreaDto(){};

public CcDistributorAreaDto(CcDistributorArea eo) {
    this.setId(eo.getId());
    this.setDistributorId(eo.getDistributorId());
    this.setAreaId(eo.getAreaId());
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

