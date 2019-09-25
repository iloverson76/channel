package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreChainDTO;
import com.deepexi.channel.domain.StoreChainQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreChainService {

    /**
     * 分页获取门店连琐关联列表
     *
     * @param query 查询参数
     * @return
     */
    List<StoreChainDTO> findList(StoreChainQuery query);

    /**
     * 保存门店连琐关联关系
     *
     * @param storeChainDTO 保存的门店连琐关联dto
     * @return
     */
    Long save(StoreChainDTO storeChainDTO);

    /**
     * 根据门店id删除门店连琐关联关系
     *
     * @param id 门店id
     * @return
     */
    Boolean removeByStoreId(Long id);

    /**
     * 根据门店id列表删除门店连琐关联关系
     *
     * @param ids 门店id列表
     * @return
     */
    Boolean removeByStoreIds(List<Long> ids);

    /**
     * 批量新增门店连锁
     *
     * @param storeChainDTOS 门店连琐关联dto列表
     * @return
     */
    Boolean saveBatch(List<StoreChainDTO> storeChainDTOS);

    /**
     * 根据连琐id列表批量删除门店连琐id列表
     *
     * @param ids
     * @return
     */
    Boolean removeByChainIds(List<Long> ids);

}
