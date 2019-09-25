package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreDistributorRelationDTO;
import com.deepexi.channel.domain.StoreDistributorRelationQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreDistributorRelationService {

    /**
     * 查询门店经销商关联
     *
     * @param id 关联表id
     * @return
     */
    StoreDistributorRelationDTO detail(Long id);

    /**
     * 新建门店经销商关联
     *
     * @param dto 门店经销商关联dto
     * @return
     */
    Boolean save(StoreDistributorRelationDTO dto);

    /**
     * 更新门店经销商关联
     *
     * @param dto 门店经销商关联dto
     * @return
     */
    Boolean update(StoreDistributorRelationDTO dto);

    /**
     * 根据id删除门店经销商关联关系
     *
     * @param id 关联表id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 根据id列表删除门店经销商关联关系
     *
     * @param ids
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 根据条件分页查询门店经销商关联列表
     *
     * @param query 查询条件
     * @return
     */
    List<StoreDistributorRelationDTO> findList(StoreDistributorRelationQuery query);

    /**
     * 批量保存门店经销商关联关系
     *
     * @param relationDTOS 门店经销商关联dto列表
     * @return
     */
    Boolean saveBatch(List<StoreDistributorRelationDTO> relationDTOS);

    /**
     * 根据门店id删除门店经销商关联关系
     *
     * @param id 门店id
     * @return
     */
    Boolean deleteByStoreId(long id);

    /**
     * 根据门店id列表删除门店经销商关联关系
     *
     * @param ids 门店id列表
     * @return
     */
    Boolean deleteByStoreIds(List<Long> ids);

    /**
     * 根据关联表id列表批量删除门店经销商关联关系
     *
     * @param pkList 关联表ids
     * @return
     */
    Boolean deleteBatchByIds(List<Long> pkList);
}
