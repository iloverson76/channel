package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreHistoryDTO;
import com.deepexi.channel.domain.StoreHistoryQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreHistoryService {

    /**
     * 分页获取门店历史列表
     *
     * @param query 查询条件
     * @return
     */
    List<StoreHistoryDTO> findPage(StoreHistoryQuery query);

    /**
     * 根据id获取门店历史详情
     *
     * @param pk 门店历史id
     * @return
     */
    StoreHistoryDTO detail(Long pk);

    /**
     * 更新门店历史
     *
     * @param dto 门店历史dto
     * @return
     */
    Boolean update(StoreHistoryDTO dto);

    /**
     * 批量更新门店历史
     *
     * @param dtos 门店历史列表
     * @return
     */
    Boolean updateBatch(List<StoreHistoryDTO> dtos);

    /**
     * 新增门店历史
     *
     * @param dto 门店历史dto
     * @return
     */
    Long create(StoreHistoryDTO dto);

    /**
     * 批量创建门店历史
     *
     * @param dtos 门店历史列表
     * @return
     */
    Boolean createBatch(List<StoreHistoryDTO> dtos);
    /**
     * 根据id删除门店历史
     *
     * @param id 门店历史id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 批量删除门店历史
     *
     * @param ids 门店历史id列表
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 根据门店id获取当前门店历史数量
     *
     * @param storeId 门店id
     * @return
     */
    Integer getStoreHistoryCountByStoreId(Long storeId);
}