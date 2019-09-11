package com.deepexi.channel.service;

import com.deepexi.channel.domain.store.StoreHistoryDTO;
import com.deepexi.channel.domain.store.StoreHistoryQuery;

import java.util.List;

/**
 * cc_store_history
 */
public interface StoreHistoryService {

    /**
    * 分页获取列表
    * @param query
    * @return
    */
    List<StoreHistoryDTO> findPage(StoreHistoryQuery query);

    /**
      获取详情
    * @return
    */
    StoreHistoryDTO detail(Long pk);

    /**
     更新dto
    * @param dto
    * @return
    */
    Boolean update(StoreHistoryDTO dto);

    /**
    * 创建dto
    * @param dto
    * @return
    */
    Long create(StoreHistoryDTO dto);

    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);

    Integer getStoreHistoryCountByStoreId(Long storeId);
}