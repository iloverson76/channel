package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreGradeRelationDTO;
import com.deepexi.channel.domain.StoreGradeRelationQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreGradeRelationService {

    /**
     * 根据条件查询门店等级关联关系列表
     *
     * @param query 查询条件
     * @return
     */
    List<StoreGradeRelationDTO> findAll(StoreGradeRelationQuery query);

    /**
     * 保存门店等级关联关系
     *
     * @param storeGradeRelationDTO 门店等级关联dto
     * @return
     */
    Long save(StoreGradeRelationDTO storeGradeRelationDTO);

    /**
     * 根据id查询门店等级关联关系
     *
     * @param id 关联表id
     * @return
     */
    StoreGradeRelationDTO detail(Long id);

    /**
     * 根据关联表id删除门店等级关联关系
     *
     * @param id 关联表id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据关联表id列表删除门店等级关联关系
     *
     * @param ids 关联表id列表
     * @return
     */
    boolean delete(List<Long> ids);

    /**
     * 根据门店id获取门店等级关联关系
     *
     * @param pk 门店id
     * @return
     */
    StoreGradeRelationDTO getStoreGradeRelationByStoreId(Long pk);

    /**
     * 根据门店id删除门店等级关联关系
     *
     * @param id 门店id
     * @return
     */
    Boolean removeByStoreId(Long id);

    /**
     * 根据门店id列表删除门店等级关联关系
     *
     * @param storeIds 门店id列表
     * @return
     */
    Boolean removeByStoreIds(List<Long> storeIds);
}
