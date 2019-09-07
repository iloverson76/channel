package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreDistributorRelationDTO;
import com.deepexi.channel.domain.store.StoreDistributorRelationQuery;

import java.util.List;

public interface StoreDistributorRelationService {
    List<StoreDistributorRelationDTO> findList(StoreDistributorRelationQuery query);
}
