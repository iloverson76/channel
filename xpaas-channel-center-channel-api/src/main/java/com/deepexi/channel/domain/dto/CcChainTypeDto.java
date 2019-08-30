package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcChainType;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_chain_type
* @author admin
*/
public class CcChainTypeDto extends CcChainType{

public CcChainTypeDto(){};

public CcChainTypeDto(CcChainType eo) {
    this.setId(eo.getId());
    this.setParentId(eo.getParentId());
    this.setLimitParent(eo.getLimitParent());
    this.setChainTypeName(eo.getChainTypeName());
    this.setChainTypeCode(eo.getChainTypeCode());
    this.setChainTypeNameEn(eo.getChainTypeNameEn());
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

