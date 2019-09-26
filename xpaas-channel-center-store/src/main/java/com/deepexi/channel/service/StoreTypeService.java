package com.deepexi.channel.service;

import com.deepexi.channel.domain.StoreTypeDTO;
import com.deepexi.channel.domain.StoreTypeQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
public interface StoreTypeService {

    /**
     * 分页获取门店类型列表
     *
     * @param query 查询条件
     * @return 门店类型列表
     */
    List<StoreTypeDTO> findPage(StoreTypeQuery query);

    /**
     * 获取门店类型详情
     *
     * @param pk 门店类型id
     * @return 店类型详情
     */
    StoreTypeDTO detail(Long pk);

    /**
     * 更新门店类型
     *
     * @param dto 门店类型dto
     * @return 更新结果boolean
     */
    Boolean update(StoreTypeDTO dto);

    /**
     * 批量更新门店类型
     *
     * @param dtos 门店类型dto列表
     * @return 更新结果boolean
     */
    Boolean updateBatch(List<StoreTypeDTO> dtos);

    /**
     * 创建门店类型
     *
     * @param dto 门店类型dto
     * @return 新建门店类型id
     */
    Long create(StoreTypeDTO dto);

    /**
     * 批量创建门店类型
     *
     * @param dtos 门店类型列表
     * @return  新建结果boolean
     */
    Boolean createBatch(List<StoreTypeDTO> dtos);

    /**
     * 批量删除
     *
     * @param ids 门店类型id列表
     * @return 删除结果boolean
     */
    Boolean delete(List<Long> ids);

    /**
     * 判断门店类型是否
     *
     * @param dto 门店类型
     * @return 编码唯一true，编码不唯一false
     */
    boolean isCodeUnique(StoreTypeDTO dto);

    /**
     * 判断名字是否唯一
     *
     * @param dto 门店类型
     * @return 名字唯一true，名字不唯一false
     */
    boolean isNameUnique(StoreTypeDTO dto);
}