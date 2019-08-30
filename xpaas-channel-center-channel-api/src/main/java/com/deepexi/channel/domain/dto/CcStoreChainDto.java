package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcStoreChain;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_store_chain
* @author admin
*/
public class CcStoreChainDto extends CcStoreChain{

public CcStoreChainDto(){};

public CcStoreChainDto(CcStoreChain eo) {
    this.setId(eo.getId());
    this.setStoreId(eo.getStoreId());
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

