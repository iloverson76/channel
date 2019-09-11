package com.deepexi.channel.domain.dto;

import com.deepexi.channel.domain.eo.CcStoreArea;
import java.util.Collection;
import java.util.List;

/**
* @desc cc_store_area
* @author admin
*/
public class CcStoreAreaDto extends CcStoreArea{

public CcStoreAreaDto(){};

public CcStoreAreaDto(CcStoreArea eo) {
    this.setId(eo.getId());
    this.setAreaId(eo.getAreaId());
    this.setStoreId(eo.getStoreId());
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

