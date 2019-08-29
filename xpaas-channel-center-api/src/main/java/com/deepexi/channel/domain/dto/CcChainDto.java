package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcChain;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_chain
* @author admin
*/
public class CcChainDto extends CcChain{

public CcChainDto(){};

public CcChainDto(CcChain eo) {
    this.setId(eo.getId());
    this.setParentId(eo.getParentId());
    this.setChainTypeId(eo.getChainTypeId());
    this.setChainName(eo.getChainName());
    this.setChainCode(eo.getChainCode());
    this.setChainNameEn(eo.getChainNameEn());
    this.setPath(eo.getPath());
    this.setDescription(eo.getDescription());
    this.setBusinessLicense(eo.getBusinessLicense());
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

