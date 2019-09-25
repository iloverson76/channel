package com.deepexi.channel.api;

import com.deepexi.channel.domain.StoreTypeDTO;
import com.deepexi.channel.domain.StoreTypeQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店类型接口
 *
 * @author mumu
 * @version 1.0
 * @date 2019/9/6 17:39
 */
@RequestMapping("/channel/storeType")
public interface StoreTypeApi {

    /**
     * 分页获取门店类型列表
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping
    List<StoreTypeDTO> listStoreType(StoreTypeQuery query);

    /**
     * 分页获取门店类型列表
     *
     * @param query
     * @return
     */
    @GetMapping("/page")
    PageBean<StoreTypeDTO> listStoreTypePage(StoreTypeQuery query);

    /**
     * 获取门店类型详情
     *
     * @param pk 门店类型id
     * @return
     */
    @GetMapping("/{id}")
    StoreTypeDTO detail(@PathVariable(value = "id") Long pk);

    /**
     * 更新门店类型
     *
     * @param dto 门店类型dto
     * @return
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id, @RequestBody StoreTypeDTO dto);

    /**
     * 创建门店类型
     *
     * @param dto 门店类型dto
     * @return
     */
    @PostMapping
    Long create(@RequestBody StoreTypeDTO dto);

    /**
     * 批量删除
     *
     * @param ids 门店类型id列表
     * @return
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 判断门店类型是否
     *
     * @param dto 门店类型,需要id与code，新增的类型id传0
     * @return
     */
    @GetMapping("/isCodeUnique")
    boolean isCodeUnique(StoreTypeDTO dto);

    /**
     * 判断名字是否唯一
     *
     * @param dto 门店类型，需要id与name，新增的类型id传0
     * @return
     */
    @GetMapping("/isNameUnique")
    boolean isNameUnique(StoreTypeDTO dto);
}