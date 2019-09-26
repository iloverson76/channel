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
     * @return 门店列表
     */
    List<StoreDTO> findPage(StoreQuery query);

    /**
     * 获取详情
     *
     * @param pk 门店id
     * @return 门店详情
     */
    StoreDTO detail(Long pk);

    /**
     * 更新门店
     *
     * @param dto 门店dto
     * @return 更新结果Boolean
     */
    Boolean update(StoreDTO dto);

    /**
     * 批量更新门店
     *
     * @param dtos
     * @return 更新结果Boolean
     */
    Boolean updateBatch(List<StoreDTO> dtos);

    /**
     * 创建门店
     *
     * @param dto 门店dto
     * @return 新增结果id
     */
    Long create(StoreDTO dto);

    /**
     * 批量新建门店
     *
     * @param dtos
     * @return 新增结果Boolean
     */
    Boolean createBatch(List<StoreDTO> dtos);

    /**
     * 批量删除
     *
     * @param ids 门店id列表
     * @return 删除结果Boolean
     */
    Boolean delete(List<Long> ids);

    /**
     * 判断门店编码是否唯一
     *
     * @param dto 门店dto
     * @return 编码唯一true，编码不唯一false
     */
    boolean isCodeUnique(StoreDTO dto);
}