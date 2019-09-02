package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreType;
import com.deepexi.channel.domain.store.StoreTypeDO;
import com.deepexi.channel.domain.store.StoreTypeDTO;
import com.deepexi.channel.domain.store.StoreTypeQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_store_type
 */
public interface StoreTypeService {

    /**
    * 分页获取列表
    * @param query
    * @return
    */
    List<StoreTypeDTO> findPage(StoreTypeQuery query);

    /**
      获取详情
    * @return
    */
    StoreTypeDTO detail(Integer pk);

    /**
     更新eo
    * @param dto
    * @return
    */
    Boolean update(StoreTypeDTO dto);

    /**
    * 创建eo
    * @param dto
    * @return
    */
    Boolean create(StoreTypeDTO dto);

    /**
     * 批量删除
    * @return
    */
    Boolean delete(List<Long> ids);

    /**
     * 判断门店类型是否
     * @param dto
     * @return
     */
    boolean isCodeUnique(StoreTypeDTO dto);

}