package com.deepexi.channel.api;

import com.deepexi.channel.domain.StoreHistoryDTO;
import com.deepexi.channel.domain.StoreHistoryQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/26 10:01
 */
@RequestMapping("/channel/storeHistory")
public interface StoreHistoryApi {
    /**
     * 分页获取门店历史列表
     *
     * @param query 查询条件
     * @return 门店历史列表
     */
    @GetMapping
    List<StoreHistoryDTO> listStoreHistory(StoreHistoryQuery query);

    /**
     * 分页获取门店历史列表
     *
     * @param query 查询条件
     * @return 门店历史列表
     */
    @GetMapping("/page")
    PageBean<StoreHistoryDTO> listStoreHistoryPage(StoreHistoryQuery query);

    /**
     * 根据id获取门店历史详情
     *
     * @param pk 门店历史id
     * @return 门店历史详情
     */
    @GetMapping("/{id}")
    StoreHistoryDTO detail(@PathVariable(value = "id") Long pk);

    /**
     * 更新门店历史
     *
     * @param id  门店历史id
     * @param dto 门店历史dto
     * @return 更新结果boolean
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id, @RequestBody StoreHistoryDTO dto);

    /**
     * 批量更新门店历史
     *
     * @param dtos 门店历史列表
     * @return 更新结果boolean
     */
    @PutMapping("/updateBatch")
    Boolean updateBatch(@RequestBody List<StoreHistoryDTO> dtos);

    /**
     * 新增门店历史
     *
     * @param dto 门店历史dto
     * @return 新建结果id
     */
    @PostMapping
    Long create(@RequestBody StoreHistoryDTO dto);

    /**
     * 批量创建门店历史
     *
     * @param dtos 门店历史列表
     * @return 新建结果boolean
     */
    @PostMapping("/createBatch")
    Boolean createBatch(@RequestBody List<StoreHistoryDTO> dtos);

    /**
     * 批量删除门店历史
     *
     * @param ids 门店历史id列表
     * @return 删除结果boolean
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 根据门店id获取当前门店历史数量
     *
     * @param id 门店id
     * @return 当前门店历史数量
     */
    @GetMapping("/getStoreHistoryCountByStoreId/{id}")
    Integer getStoreHistoryCountByStoreId(@PathVariable(value = "id") Long id);
}