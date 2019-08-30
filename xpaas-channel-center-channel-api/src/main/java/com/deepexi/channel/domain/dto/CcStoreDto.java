package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcStore;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_store
* @author admin
*/
public class CcStoreDto extends CcStore{

public CcStoreDto(){};

public CcStoreDto(CcStore eo) {
    this.setId(eo.getId());
    this.setStoreName(eo.getStoreName());
    this.setStoreCode(eo.getStoreCode());
    this.setStoreNameEn(eo.getStoreNameEn());
    this.setClientName(eo.getClientName());
    this.setClientCode(eo.getClientCode());
    this.setStoreAddress(eo.getStoreAddress());
    this.setStorePhone(eo.getStorePhone());
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

