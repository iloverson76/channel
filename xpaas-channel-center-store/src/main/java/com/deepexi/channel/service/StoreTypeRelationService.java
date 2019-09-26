package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreTypeRelationDTO;
import com.deepexi.channel.domain.StoreTypeRelationQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreTypeRelationService {
    /**
     * 保存门店类型关联关系
     *
     * @param storeTypeRelationDTO 门店类型关联dto
     * @return 新增门店型关联关系id
     */
    Long save(StoreTypeRelationDTO storeTypeRelationDTO);

    /**
     * 根据门店id获取门店类型关联关系
     *
     * @param pk 门店id
     * @return 门店类型关联关系详情
     */
    StoreTypeRelationDTO getStoreTypeRelationByStoreId(Long pk);

    /**
     * 根据条件查询门店类型关联关系列表
     *
     * @param storeGradeRelationQuery 查询条件
     * @return 门店类型关联关系列表
     */
    List<StoreTypeRelationDTO> findAll(StoreTypeRelationQuery storeGradeRelationQuery);

    /**
     * 根据门店id删除门店类型关联关系
     *
     * @param id 门店id
     * @return 删除结果boolean
     */
    Boolean removeByStoreId(Long id);

    /**
     * 根据门店id列表删除门店类型关联关系
     *
     * @param storeIds 门店id列表
     * @return 删除结果boolean
     */
    Boolean removeByStoreIds(List<Long> storeIds);
}
