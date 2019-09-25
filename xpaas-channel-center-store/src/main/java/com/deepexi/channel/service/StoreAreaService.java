package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreAreaDTO;
import com.deepexi.channel.domain.StoreAreaQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreAreaService {

    /**
     * 分页查询门店区域关联表
     *
     * @param query 查询条件
     * @return
     */
    List<StoreAreaDTO> findList(StoreAreaQuery query);

    /**
     * 新增门店区域关联关系
     *
     * @param storeAreaDTO 门店区域关联dto
     * @return
     */
    Long save(StoreAreaDTO storeAreaDTO);

    /**
     * 根据门店id删除门店区域关联关系
     *
     * @param id 门店id
     * @return
     */
    Boolean removeByStoreId(Long id);

    /**
     * 根据门店id获取门店区域关联关系列表
     *
     * @param storeId 门店id
     * @return
     */
    List<StoreAreaDTO> getStoreAreaByStoreId(Long storeId);


    /**
     * 根据门店id列表删除门店区域关联关系
     *
     * @param ids
     * @return
     */
    Boolean removeByStoreIds(List<Long> ids);

    /**
     * 批量新增门店区域关联关系
     *
     * @param list 门店区域关联关系列表
     * @return
     */
    Boolean saveBatch(List<StoreAreaDTO> list);
}