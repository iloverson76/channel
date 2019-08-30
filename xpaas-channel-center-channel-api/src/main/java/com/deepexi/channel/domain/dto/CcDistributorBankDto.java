package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcDistributorBank;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_distributor_bank
* @author admin
*/
public class CcDistributorBankDto extends CcDistributorBank{

public CcDistributorBankDto(){};

public CcDistributorBankDto(CcDistributorBank eo) {
    this.setId(eo.getId());
    this.setDistributorId(eo.getDistributorId());
    this.setBankAccountId(eo.getBankAccountId());
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

