package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreDTO;
import com.deepexi.channel.domain.StoreQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreService {

    /**
     * 分页获取门店列表
     *
     * @param query 查询条件
     * @return
     */
    List<StoreDTO> findPage(StoreQuery query);

    /**
     * 获取详情
     *
     * @param pk 门店id
     * @return
     */
    StoreDTO detail(Long pk);

    /**
     * 更新门店
     *
     * @param dto 门店dto
     * @return
     */
    Boolean update(StoreDTO dto);

    /**
     * 创建门店
     *
     * @param dto 门店dto
     * @return
     */
    Long create(StoreDTO dto);

    /**
     * 批量删除
     *
     * @param ids 门店id列表
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 判断门店编码是否唯一
     *
     * @param dto 门店dto
     * @return
     */
    boolean isCodeUnique(StoreDTO dto);
}