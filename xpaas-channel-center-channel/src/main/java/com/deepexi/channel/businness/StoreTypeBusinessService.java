package com.deepexi.channel.businness;

import com.deepexi.channel.domain.store.StoreTypeDTO;

import java.util.List;

public interface StoreTypeBusinessService {
    Boolean deleteStoreType(List<Long> ids);
}
