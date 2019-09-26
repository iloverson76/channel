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
@RequestMapping("/channel/store")
public interface StoreApi {

    /**
     * 获取门店列表
     *
     * @param query 查询条件
     * @return 门店列表
     */
    @GetMapping
    List<StoreDTO> listStore(StoreQuery query);

    /**
     * 分页获取门店列表
     *
     * @param query
     * @return 门店列表
     */
    @GetMapping("/page")
    PageBean<StoreDTO> listStorePage(StoreQuery query);

    /**
     * 获取详情
     *
     * @param pk 门店id
     * @return 门店详情
     */
    @GetMapping("/{id}")
    StoreDTO detail(@PathVariable(value = "id") Long pk);

    /**
     * 更新门店
     *
     * @param id  门店id
     * @param dto 门店dto
     * @return 更新结果Boolean
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id, @RequestBody StoreDTO dto);

    /**
     * 批量更新门店
     *
     * @param dtos
     * @return 更新结果Boolean
     */
    @PutMapping("/updateBatch")
    Boolean updateBatch(@RequestBody List<StoreDTO> dtos);

    /**
     * 创建门店
     *
     * @param dto 门店dto
     * @return 新增结果id
     */
    @PostMapping
    Long create(@RequestBody StoreDTO dto);


    /**
     * 批量新建门店
     *
     * @param dtos
     * @return 新增结果Boolean
     */
    @PostMapping("/createBatch")
    Boolean createBatch(@RequestBody List<StoreDTO> dtos);

    /**
     * 批量删除
     *
     * @param ids 门店id列表
     * @return 删除结果Boolean
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 判断门店编码是否唯一，需要id与name，新增的类型id传0
     *
     * @param dto 门店dto
     * @return 编码唯一true，编码不唯一false
     */
    @GetMapping("/isCodeUnique")
    boolean isCodeUnique(StoreDTO dto);
}