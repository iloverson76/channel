package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcDistributor;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_distributor
* @author admin
*/
public class CcDistributorDto extends CcDistributor{

public CcDistributorDto(){};

public CcDistributorDto(CcDistributor eo) {
    this.setId(eo.getId());
    this.setDistributorType(eo.getDistributorType());
    this.setDistributorName(eo.getDistributorName());
    this.setDistributorNameEn(eo.getDistributorNameEn());
    this.setDistributorCode(eo.getDistributorCode());
    this.setBusinessType(eo.getBusinessType());
    this.setBusinessLicenseNo(eo.getBusinessLicenseNo());
    this.setBusinessLicense(eo.getBusinessLicense());
    this.setCreditValue(eo.getCreditValue());
    this.setPrincipalName(eo.getPrincipalName());
    this.setPrincipalIdCard(eo.getPrincipalIdCard());
    this.setQualification(eo.getQualification());
    this.setAddress(eo.getAddress());
    this.setEnable(eo.getEnable());
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

