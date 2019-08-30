package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcBank;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_bank
* @author admin
*/
public class CcBankDto extends CcBank{

public CcBankDto(){};

public CcBankDto(CcBank eo) {
    this.setId(eo.getId());
    this.setBankName(eo.getBankName());
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

