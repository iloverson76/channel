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
     * @param query
     * @return
     */
    List<StoreTypeDTO> findPage(StoreTypeQuery query);

    /**
     * 获取门店类型详情
     *
     * @param pk 门店类型id
     * @return
     */
    StoreTypeDTO detail(Long pk);

    /**
     * 更新门店类型
     *
     * @param dto 门店类型dto
     * @return
     */
    Boolean update(StoreTypeDTO dto);

    /**
     * 创建门店类型
     *
     * @param dto 门店类型dto
     * @return
     */
    Long create(StoreTypeDTO dto);

    /**
     * 批量删除
     *
     * @param ids 门店类型id列表
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 判断门店类型是否
     *
     * @param dto 门店类型
     * @return
     */
    boolean isCodeUnique(StoreTypeDTO dto);

    /**
     * 判断名字是否唯一
     *
     * @param dto 门店类型
     * @return
     */
    boolean isNameUnique(StoreTypeDTO dto);
}