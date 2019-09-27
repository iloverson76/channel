package com.deepexi.channel.api;

import com.deepexi.channel.domain.DistributorGradeRelationDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorGradeRelationApi {

    /**
     * 创建经销商与等级的关联关系
     * @param dto 新增实体
     * @return 新纪录ID
     */
    @PostMapping
    long create(@RequestBody DistributorGradeRelationDTO dto);

    /**
     * 批量创建经销商与等级的关联关系
     * @param dtoList 新增实体集合
     * @return 是否成功
     */
    @PostMapping("/createBatch")
    boolean createBatch(@RequestBody List<DistributorGradeRelationDTO> dtoList);

    /**
     * 根据经销商ID删除与等级的关联关系
     * @param distributorId 经销商ID
     * @return 成功删除的记录数量
     */
    @DeleteMapping("/distributorId/{distributorId}")
    int deleteByDistributorId(@PathVariable(value = "distributorId") long distributorId);

    /**
     * 根据经销商ID集合批量删除与等级的关联关系
     * @param distributorIdList 经销商ID集合
     * @return 成功删除的记录数量
     */
    @DeleteMapping("/deleteBatch/distributorIds")
    int deleteBatchByDistributorIds(@RequestBody List<Long> distributorIdList);

    /**
     * 根据经销商ID和等级ID查找关联关系
     * @param distributorId 经销商ID
     * @param gradeId 等级ID
     * @return 关联关系
     */
    @GetMapping("/{distributorId}/{gradeId}")
    DistributorGradeRelationDTO findOne(@PathVariable(value = "distributorId") long distributorId,
                                        @PathVariable(value = "gradeId") long gradeId);

    /**
     * 根据经销商ID集合查找与等级的所有关联关系
     * @param distributorIds 经销商ID集合
     * @return 关联关系集合
     */
    @GetMapping("/findAllByDistributorIds")
    List<DistributorGradeRelationDTO> findAllByDistributorIds(@RequestBody List<Long> distributorIds);

    /**
     * 根据实体ID集合批量更新经销商与等级的关联关系
     * @param dtoList 实体集合
     * @return 成功删除的记录数量
     */
    @PutMapping("/updateBatch")
    boolean updateBatchById(@RequestBody List<DistributorGradeRelationDTO> dtoList);

    /**
     * 根据实体ID集合批量删除经销商与等级的关联关系
     * @param dtoList 实体集合
     * @return 成功删除的记录数量
     */
    @PutMapping("/updateBatch/distributorIds")
    boolean updateBatchByDistributorId(@RequestBody List<DistributorGradeRelationDTO> dtoList);

    /**
     * 根据等级ID查找所有与经销商的关联关系
     * @param gradeId 等级ID
     * @return 关联关系集合
     */
    @GetMapping("/findAllByGradeId/{gradeId}")
    List<DistributorGradeRelationDTO> findAllByGradeId(@PathVariable(value = "gradeId") Long gradeId);

    /**
     * 根据等级ID集合查找所有与经销商的关联关系
     * @param gradeIds 等级ID集合
     * @return 关联关系集合
     */
    @GetMapping("/findAllByGradeIds")
    List<DistributorGradeRelationDTO> findAllByGradeIds(@RequestBody List<Long> gradeIds);

    /**
     * 根据体系ID查找所有与经销商的关联关系
     * @param systemIds 体系ID集合
     * @return 关联关系集合
     */
    @GetMapping("/findAllBySystemIds")
    List<DistributorGradeRelationDTO> findAllBySystemIds(@RequestBody List<Long> systemIds);
}