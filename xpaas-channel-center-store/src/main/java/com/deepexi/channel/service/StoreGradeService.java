package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreGradeDTO;
import com.deepexi.channel.domain.StoreGradeQuery;

import java.util.List;

/**
 * cc_store_grade
 */
public interface StoreGradeService {

    /**
     * 分页获取门店等级列表
     *
     * @param query 查询条件
     * @return
     */
    List<StoreGradeDTO> findPage(StoreGradeQuery query);

    /**
     * 获取门店等级详情
     *
     * @param pk 门店等级id
     * @return
     */
    StoreGradeDTO detail(Long pk);

    /**
     * 更新门店等级
     *
     * @param dto 门店等级dto
     * @return
     */
    Boolean update(StoreGradeDTO dto);

    /**
     * 创建门店等级
     *
     * @param dto 门店等级dto
     * @return
     */
    Long create(StoreGradeDTO dto);

    /**
     * 批量删除
     *
     * @param ids 门店等级id列表
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 判断编码是否唯一
     *
     * @param dto 门店等级dto
     * @return
     */
    boolean isCodeUnique(StoreGradeDTO dto);

    /**
     * 判断名称是否唯一
     *
     * @param dto 门店等级dto
     * @return
     */
    boolean isNameUnique(StoreGradeDTO dto);
}