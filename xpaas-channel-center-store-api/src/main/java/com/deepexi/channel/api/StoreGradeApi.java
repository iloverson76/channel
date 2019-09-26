package com.deepexi.channel.api;

import com.deepexi.channel.domain.StoreGradeDTO;
import com.deepexi.channel.domain.StoreGradeQuery;
import com.deepexi.util.pageHelper.PageBean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店等级接口
 *
 * @author mumu
 * @version 1.0
 * @date 2019/9/25 19:57
 */
@RequestMapping("/channel/storeGrade")
public interface StoreGradeApi {

    /**
     * 分页获取门店等级列表
     *
     * @param query 查询条件
     * @return
     */
    @GetMapping
    List<StoreGradeDTO> listStoreGrade(StoreGradeQuery query);


    /**
     * 分页获取门店等级列表
     *
     * @param query
     * @return
     */
    @GetMapping("/page")
    PageBean<StoreGradeDTO> listStoreGradePage(StoreGradeQuery query);

    /**
     * 获取门店等级详情
     *
     * @param pk 门店等级id
     * @return
     */
    @GetMapping("/{id}")
    StoreGradeDTO detail(@PathVariable(value = "id") Long pk);

    /**
     * 更新门店等级
     *
     * @param id  门店等级id
     * @param dto 门店等级dto
     * @return
     */
    @PutMapping("/{id}")
    Boolean update(@PathVariable(value = "id") Long id, @RequestBody StoreGradeDTO dto);

    /**
     * 创建门店等级
     *
     * @param dto 门店等级dto
     * @return
     */
    @PostMapping
    Long create(@RequestBody StoreGradeDTO dto);

    /**
     * 批量删除
     *
     * @param ids 门店等级id列表
     * @return
     */
    @DeleteMapping
    Boolean delete(@RequestBody List<Long> ids);

    /**
     * 判断编码是否唯一
     *
     * @param dto 门店等级dto
     * @return
     */
    @GetMapping("/isCodeUnique")
    boolean isCodeUnique(StoreGradeDTO dto);

    /**
     * 判断名称是否唯一
     *
     * @param dto 门店等级dto
     * @return
     */
    @GetMapping("/isNameUnique")
    boolean isNameUnique(StoreGradeDTO dto);
}