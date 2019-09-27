package com.deepexi.channel.api;


import com.deepexi.channel.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorBusinessApi {

    /**
     * 创建经销商
     * @param dto 新增实体
     * @return 新增记录的ID
     */
    @PostMapping("/butorBusi")
   long create(@RequestBody DistributorBusiDTO dto);

    /**
     * 校验经销商编码是否重复
     * @param distributorCode 新增编码
     */
    @GetMapping("/butorBusi/validateDistributorCode/{distributorCode}")
   void validateDistributorCode(@PathVariable(value = "distributorCode") String distributorCode);

    /**
     * 校验经销商名称是否重复
     * @param distributorName 新增名称
     */
    @GetMapping("/butorBusi/validateDistributorName/{distributorName}")
   void validateDistributorName(@PathVariable(value = "distributorName") String distributorName);

    /**
     * 校验经销商英文名是否重复
     * @param distributorNameEn 新增英文名称
     */
    @GetMapping("/butorBusi/validateDistributorNameEn/{distributorNameEn}")
   void validateDistributorNameEn(@PathVariable(value = "distributorNameEn") String distributorNameEn);

    /**
     * 批量删除经销商
     * @param idList 经销商ID集合
     * @param forceDelete 是否强制删除 0否 1是
     * @return 是否成功
     */
    @DeleteMapping("/butorBusi/deleteBatch/{forceDelete}")
   boolean deleteBatchByIds(@RequestBody List<Long> idList, @PathVariable(value = "forceDelete") Integer forceDelete);

    /**
     * 校验经销商是否有下级
     * @param distributorIdList 经销商ID集合
     */
    @GetMapping("/butorBusi/validateHasChildren/")
   void validateHasChildren(@RequestBody List<Long> distributorIdList);

    /**
     * 校验经销商是否有门店关联
     * @param butorIdList 经销商ID集合
     */
    @GetMapping("/butorBusi/validateHasStores")
   void validateHasStores(@RequestBody List<Long> butorIdList);

    /**
     * 删除关联的门店
     * @param distributorIdList 经销商ID集合
     * @return
     */
    @DeleteMapping("/butorBusi/deleteStores")
    boolean deleteStores(@RequestBody List<Long> distributorIdList);

    /**
     * 经销商分页查询
     * @param query 查询条件
     * @return 经销商集合
     */
    @GetMapping("/butorBusi/page")
    List<DistributorBusiDTO> findPage(@RequestBody DistributorQuery query);

    /**
     * 更新经销商
     * @param dto 更新实体
     * @return 是否成功
     */
    @PutMapping("/butorBusi")
    boolean update(@RequestBody DistributorBusiDTO dto);

    /**
     * 获取关联的区域信息
     * @param distributorId 经销商ID
     * @return 区域集合
     */
    @GetMapping("/butorBusi/getAreaInfo/{distributorId}")
    List<AreaDTO> getAreaInfo(@PathVariable(value = "distributorId") Long distributorId);

    /**
     * 获取关联的银行账户信息
     * @param distributorId
     * @return 银行账户集合
     */
    @GetMapping("/butorBusi/getBankAccountInfo/{distributorId}")
    List<BankAccountDTO> getBankAccountInfo(Long distributorId);

    /**
     * 获取经销商详细信息
     * @param id 经销商ID
     * @return 经销商实体
     */
    @GetMapping("/butorBusi/{id}")
    DistributorBusiDTO detail(@PathVariable(value = "id") Long id);

    /**
     * 获取上级经销商
     * @param gradeId 等级ID
     * @return 经销商集合
     */
    @GetMapping("/butorBusi/listParentByGrade/{gradeId}")
    List<DistributorBusiDTO> listParentDistributorsByGrade(@PathVariable(value = "gradeId") Long gradeId);

    /**
     * 获取关联的等级信息
     * @param distributorId 经销商ID
     * @return 等级集合
     */
    @GetMapping("/butorBusi/getGradeInfo/{distributorId}")
    List<GradeInfoDTO> getGradeInfo(@PathVariable(value = "distributorId") Long distributorId);
}