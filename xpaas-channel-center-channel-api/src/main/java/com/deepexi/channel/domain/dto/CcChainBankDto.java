package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcChainBank;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_chain_bank
* @author admin
*/
public class CcChainBankDto extends CcChainBank{

public CcChainBankDto(){};

public CcChainBankDto(CcChainBank eo) {
    this.setId(eo.getId());
    this.setBankAccountId(eo.getBankAccountId());
    this.setChainId(eo.getChainId());
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

