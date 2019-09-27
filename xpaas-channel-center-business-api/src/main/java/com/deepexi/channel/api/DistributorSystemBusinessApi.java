package com.deepexi.channel.api;

import com.deepexi.channel.domain.DistributorGradeSystemDTO;
import com.deepexi.channel.domain.DistributorGradeSystemQuery;
import com.deepexi.channel.domain.StoreDistributorDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorSystemBusinessApi {

    /**
     * 创建经销商体系
     * @param dto 新增实体
     * @return 新增记录的ID
     */
    @PostMapping("/systemBusi")
    Long create(@RequestBody DistributorGradeSystemDTO dto);

    /**
     * 分页查询
     * @param query 查询条件
     * @return 体系集合
     */
    @GetMapping("/systemBusi/page")
    List<DistributorGradeSystemDTO> findPage(@RequestBody DistributorGradeSystemQuery query);

    /**
     * 获取详情
     * @param pk 体系ID
     * @return 体系实体
     */
    @GetMapping("/systemBusi/detail/{id}")
    DistributorGradeSystemDTO detail(@PathVariable(value = "id") Long pk);

    /**
     * 根据经销商id获取所属所有等级体系
     * @param distributorId 经销商ID
     * @return 与门店的关联集合
     */
    @GetMapping("/systemBusi/systemButerId/{distributorId}")
    List<StoreDistributorDTO> getDistributorGradeSystemByDistributorId(@PathVariable(value = "distributorId") long distributorId);

    /**
     * 批量删除体系
     * @param idList 体系ID集合
     * @param forceDelete 是否强制删除 0否 1是
     * @return 是否成功
     */
    @DeleteMapping("/systemBusi/deleteBatch/{forceDelete}")
    boolean deleteBatchByIds(@RequestBody List<Long> idList, @PathVariable(value = "forceDelete") Integer forceDelete);

    /**
     * 校验体系是否有等级挂载
     * @param systemIdList 体系ID集合
     */
    @GetMapping("/systemBusi/validateHasGrades")
    void validateHasGrades(@RequestBody List<Long> systemIdList);

    /**
     * 校验体系编码是否重复
     * @param systemCode 新增的体系编码
     */
    @GetMapping("/systemBusi/validateGradeSystemCode/{systemCode}")
    void validateGradeSystemCode(@PathVariable(value = "systemCode") String systemCode);

    /**
     * 校验体系中文名称是否重复
     * @param systemName 新增的体系中文名称
     */
    @GetMapping("/systemBusi/validateGradeSystemName/{systemName}")
    void validateGradeSystemName(@PathVariable(value = "systemName") String systemName);

    /**
     * 校验体系英文名称是否重复
     * @param systemNameEn 新增的体系英文名称
     */
    @GetMapping("/systemBusi/validateGradeSystemNameEn/{systemNameEn}")
    void validateGradeSystemNameEn(@PathVariable(value = "systemNameEn") String systemNameEn);

    /**
     * 更新体系
     * @param dto 更新实体
     * @return 是否成功
     */
    @PutMapping("/systemBusi")
    Boolean update(@RequestBody DistributorGradeSystemDTO dto);
}