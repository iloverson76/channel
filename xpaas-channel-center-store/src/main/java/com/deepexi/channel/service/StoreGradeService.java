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
     * @return 门店等级列表
     */
    List<StoreGradeDTO> findPage(StoreGradeQuery query);

    /**
     * 获取门店等级详情
     *
     * @param pk 门店等级id
     * @return 门店等级详情
     */
    StoreGradeDTO detail(Long pk);

    /**
     * 更新门店等级
     *
     * @param dto 门店等级dto
     * @return 更新结果boolean
     */
    Boolean update(StoreGradeDTO dto);

    /**
     * 批量更新门店等级
     *
     * @param dtos 门店等级dto列表
     * @return 更新结果boolean
     */
    Boolean updateBatch(List<StoreGradeDTO> dtos);

    /**
     * 创建门店等级
     *
     * @param dto 门店等级dto
     * @return 创建结果id
     */
    Long create(StoreGradeDTO dto);

    /**
     * 批量创建门店等级
     *
     * @param dtos 门店等级列表
     * @return 创建结果boolean
     */
    Boolean createBatch(List<StoreGradeDTO> dtos);

    /**
     * 批量删除
     *
     * @param ids 门店等级id列表
     * @return 删除结果boolean
     */
    Boolean delete(List<Long> ids);

    /**
     * 判断编码是否唯一
     *
     * @param dto 门店等级dto
     * @return 编码唯一true，编码不唯一false
     */
    boolean isCodeUnique(StoreGradeDTO dto);

    /**
     * 判断名称是否唯一
     *
     * @param dto 门店等级dto
     * @return 名称唯一true，名称不唯一false
     */
    boolean isNameUnique(StoreGradeDTO dto);
}