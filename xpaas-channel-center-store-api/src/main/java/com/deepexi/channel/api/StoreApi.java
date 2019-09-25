package com.deepexi.channel.api;

import com.deepexi.channel.domain.StoreDTO;
import com.deepexi.channel.domain.StoreQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/25 20:29
 */
public interface StoreApi {

    /**
     * 获取门店列表
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping
    List<StoreDTO> listStore(StoreQuery query);

    /**
     * 分页获取门店列表
     *
     * @param query
     * @return
     */
    @GetMapping("/page")
    PageBean<StoreDTO> listStorePage(StoreQuery query);

    /**
     * 获取详情
     *
     * @param pk 门店id
     * @return
     */
    @GetMapping("/{id}")
    StoreDTO detail(@PathVariable(value = "id") Long pk);

    /**
     * 更新门店
     *
     * @param dto 门店dto
     * @return
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id, @RequestBody StoreDTO dto);

    /**
     * 创建门店
     *
     * @param dto 门店dto
     * @return
     */
    @PostMapping
    Long create(@RequestBody StoreDTO dto);

    /**
     * 批量删除
     *
     * @param ids 门店id列表
     * @return
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 判断门店编码是否唯一，需要id与name，新增的类型id传0
     *
     * @param dto 门店dto
     * @return
     */
    @GetMapping("/isCodeUnique")
    boolean isCodeUnique(StoreDTO dto);
}