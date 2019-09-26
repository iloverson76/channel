package com.deepexi.channel.domain.api;

import com.deepexi.channel.domain.DistributorAreaRelationDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorAreaRelationApi {
    /**
     * 获取经销商和区域关联关系
     * @param dto 新增实体
     * @return 新增记录ID
     */
    @PostMapping
    Long create(@RequestBody DistributorAreaRelationDTO dto);

    /**
     * 批量新增经销商和区域关联关系
     * @param dtoList 新增实体集合
     * @return 是否成功
     */
    @PostMapping("/createBatch")
    boolean createBatch(@RequestBody List<DistributorAreaRelationDTO> dtoList);

    /**
     * 根据经销商ID集合批量删除与区域的关联关系
     * @param distributorIdList 经销商ID集合
     * @return 成功删除的记录数
     */
    @DeleteMapping("/deleteBatch")
    int deleteBatchByDistributorIds(@RequestBody List<Long> distributorIdList);

    /**
     * 根据区域ID集合删除与经销商的关联关系
     * @param areaIdList 区域ID集合
     * @return 成功删除的记录数
     */
    @DeleteMapping("/deleteBatch/areaIds")
    int deleteBatchByAreaIds(@RequestBody List<Long> areaIdList);

    /**
     * 根据经销商ID查找所有与区域的关联关系
     * @param butorId 经销商ID
     * @return 关联关系集合
     */
    @GetMapping("/findAllByDistributorId/{distributorId}")
    List<DistributorAreaRelationDTO> findAllByDistributorId(@PathVariable(value = "distributorId") Long butorId);

    /**
     * 根据区域ID集合查找所有与经销商的关联关系
     * @param areaIdList 区域ID集合
     * @return 关联关系集合
     */
    @GetMapping("/findAllByAreaIds/areaIds")
    List<DistributorAreaRelationDTO> findAllByAreaIds(@RequestBody List<Long> areaIdList);

    /**
     * 根据经销商ID集合删除所有与区域的关联关系
     * @param distributorId
     * @return 成功删除的记录数
     */
    @DeleteMapping("/deleteByDistributorId/{distributorId}")
    Boolean deleteByDistributorId(@PathVariable(value = "distributorId") Long distributorId);
}