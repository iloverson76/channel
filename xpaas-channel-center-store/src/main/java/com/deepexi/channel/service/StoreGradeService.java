package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreGradeDTO;
import com.deepexi.channel.domain.StoreGradeQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
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
     * 批量更新门店等级
     *
     * @param dtos 门店等级dto列表
     * @return
     */
    Boolean updateBatch(List<StoreGradeDTO> dtos);

    /**
     * 创建门店等级
     *
     * @param dto 门店等级dto
     * @return
     */
    Long create(StoreGradeDTO dto);

    /**
     * 批量创建门店等级
     *
     * @param dtos 门店等级列表
     * @return
     */
    Boolean createBatch(List<StoreGradeDTO> dtos);

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