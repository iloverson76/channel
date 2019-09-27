package com.deepexi.channel.service;

import com.deepexi.channel.domain.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorBusinessService {

    /**
     * 创建经销商
     * @param dto 新增实体
     * @return 新增记录的ID
     */
   long create(DistributorBusiDTO dto);

    /**
     * 校验经销商编码是否重复
     * @param distributorCode 新增编码
     */
   void validateDistributorCode(String distributorCode);

    /**
     * 校验经销商名称是否重复
     * @param distributorName 新增名称
     */
   void validateDistributorName(String distributorName);

    /**
     * 校验经销商英文名是否重复
     * @param distributorNameEn 新增英文名称
     */
   void validateDistributorNameEn(String distributorNameEn);

    /**
     * 批量删除经销商
     * @param idList 经销商ID集合
     * @param forceDelete 是否强制删除 0否 1是
     * @return 是否成功
     */
   boolean deleteBatchByIds(List<Long> idList,Integer forceDelete);

    /**
     * 校验经销商是否有下级
     * @param butorIdList 经销商ID集合
     */
   void validateHasChildren(List<Long> butorIdList);

    /**
     * 校验经销商是否有门店关联
     * @param butorIdList 经销商ID集合
     */
   void validateHasStores(List<Long> butorIdList);

    /**
     * 删除关联的门店
     * @param distributorIdList 经销商ID集合
     * @return
     */
    boolean deleteStores(List<Long> distributorIdList);

    /**
     * 经销商分页查询
     * @param query 查询条件
     * @return 经销商集合
     */
    List<DistributorBusiDTO> findPage(DistributorQuery query);

    /**
     * 更新经销商
     * @param clone 更新实体
     * @return 是否成功
     */
    boolean update(DistributorBusiDTO clone);

    /**
     * 获取关联的区域信息
     * @param distributorId 经销商ID
     * @return 区域集合
     */
    List<AreaDTO> getAreaInfo(Long distributorId);

    /**
     * 获取关联的银行账户信息
     * @param distributorId
     * @return 银行账户集合
     */
    List<BankAccountDTO> getBankAccountInfo(Long distributorId);

    /**
     * 获取经销商详细信息
     * @param id 经销商ID
     * @return 经销商实体
     */
    DistributorBusiDTO detail(Long id);

    /**
     * 获取上级经销商
     * @param gradeId 等级ID
     * @return 经销商集合
     */
    List<DistributorBusiDTO> listParentDistributorsByGrade(Long gradeId);

    /**
     * 获取关联的等级信息
     * @param distributorId 经销商ID
     * @return 等级集合
     */
    List<GradeInfoDTO> getGradeInfo(Long distributorId);
}