package com.deepexi.channel.service;

import com.deepexi.channel.domain.DistributorBankAccountRelationDTO;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorBankAccountRelationService {

    /**
     * 批量创建经销商与银行账户的关联关系
     * @param dtoList 新增实体
     * @return 是否成功
     */
    boolean batchCreate(List<DistributorBankAccountRelationDTO> dtoList);

    /**
     * 批量删除经销商与银行的关联关系
     * @param idList 实体ID集合
     * @return 是否成功
     */
    boolean deleteBatchByDistributorIds(List<Long> idList);

    /**
     * 根据经销商ID批量删除所有与银行账户的关联关系
     * @param distributorId 经销商ID
     * @return 成功删除的记录数
     */
    int deleteBatchByDistributorId(Long distributorId);

    /**
     * 根据银行账户ID集合查找所有与经销商的关联关系
     * @param bankAccountIds
     * @return 关联关系集合
     */
    List<DistributorBankAccountRelationDTO> findAllByDistributorIds(List<Long> bankAccountIds);

}