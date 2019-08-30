package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcBankAccount;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_bank_account
* @author admin
*/
public class CcBankAccountDto extends CcBankAccount{

public CcBankAccountDto(){};

public CcBankAccountDto(CcBankAccount eo) {
    this.setId(eo.getId());
    this.setBankId(eo.getBankId());
    this.setBankBranchName(eo.getBankBranchName());
    this.setAccountNo(eo.getAccountNo());
    this.setBankCode(eo.getBankCode());
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

