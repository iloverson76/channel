package com.deepexi.channel.api;

import com.deepexi.channel.domain.DistributorGradeDTO;
import com.deepexi.channel.domain.DistributorGradeQuery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorGradeApi {

    /**
     * 创建经销商等级
     * @param dto 新增实体
     * @return 新增记录ID
     */
    @PostMapping("/grade")
    Long create(@RequestBody DistributorGradeDTO dto);

    /**
     * 根据ID获取等级
     * @param pk 主键
     * @return 等级
     */
    @GetMapping("/grade/{id}")
    DistributorGradeDTO getById(@PathVariable(value = "id") Long pk);

    /**
     * 更新经销商等级
     * @param dto 更新实体
     * @return 是否成功
     */
    @PutMapping("/grade")
    Boolean updateById(@RequestBody DistributorGradeDTO dto);

    /**
     * 批量删除经销商等级
     * @param ids ID集合
     * @return 是否成功
     */
    @DeleteMapping("/grade/deleteBatch")
    Boolean deleteBatchByIds(@RequestBody List<Long> ids);

    /**
     * 删除经销商等级
     * @param id 主键
     * @return 是否成功
     */
    @DeleteMapping("/grade/{id}")
    Boolean deleteById(@PathVariable(value = "id") Long id);

    /**
     * 分页查询等级列表
     * @param query 查询条件
     * @return 等级集合
     */
    @GetMapping("/grade/page")
    List<DistributorGradeDTO> findPage(@RequestBody DistributorGradeQuery query);

    /**
     * 根据体系ID查找所有的挂载的等级
     * @param systemId 体系ID
     * @return 等级集合
     */
    @GetMapping("/grade/findAllBySystem")
    List<DistributorGradeDTO> findAllBySystem(Long systemId);

    /**
     * 批量更新等级
     * @param dtoList 等级集合
     * @return 是否成功
     */
    @PutMapping("/grade/updateBatch")
    boolean updateBatchById(@RequestBody List<DistributorGradeDTO> dtoList);

    /**
     * 根据体系ID查找所有挂载的等级编码
     * @param systemId 体系ID
     * @return 等级编码集合
     */
    @GetMapping("/grade/listDistributorGradeCode")
    List<String> listDistributorGradeCode(Long systemId);

    /**
     * 根据体系ID查找所有的等级名称
     * @param systemId 体系ID
     * @return 等级名称集合
     */
    @GetMapping("/grade/listDistributorGradeName")
    List<String> listDistributorGradeName(Long systemId);

    /**
     * 根据体系ID查找所有的等级英文名称
     * @param systemId 体系ID
     * @return 等级英文名集合
     */
    @GetMapping("/grade/listDistributorGradeNameEn")
    List<String> listDistributorGradeNameEn(Long systemId);
}