package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreDTO;
import com.deepexi.channel.domain.StoreQuery;

import java.util.List;

/**
 * cc_store
 */
public interface StoreService {

    /**
    * 分页获取列表
    * @param query
    * @return
    */
    List<StoreDTO> findPage(StoreQuery query);

    /**
      获取详情
    * @return
    */
    StoreDTO detail(Long pk);

    /**
     更新dto
    * @param dto
    * @return
    */
    Boolean update(StoreDTO dto);

    /**
    * 创建dto
    * @param dto
    * @return
    */
    Long create(StoreDTO dto);

    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);

    boolean isCodeUnique(StoreDTO dto);
}