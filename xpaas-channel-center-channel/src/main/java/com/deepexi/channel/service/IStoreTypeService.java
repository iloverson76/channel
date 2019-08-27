package com.deepexi.channel.service;


import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeQuery;

import java.util.List;

/**
 * <p>
 * 连锁类型表 服务类
 * </p>
 *
 * @author jobob
 * @since 2019-08-26
 */
public interface IStoreTypeService {

    StoreTypeDTO getStoreType(Long id);

    List<StoreTypeDTO> listStoreType(StoreTypeQuery query);

    Boolean insert(StoreTypeDTO dto);

    Boolean update(StoreTypeDTO dto);

    Boolean delete(List<Long> ids);
}
