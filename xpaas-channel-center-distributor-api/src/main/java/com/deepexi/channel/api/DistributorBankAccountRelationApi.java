package com.deepexi.channel.api;

import com.deepexi.channel.domain.DistributorBankAccountRelationDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chp
 * @version 1.0
 * @date 2019-09-26 15:39
 */
public interface DistributorBankAccountRelationApi {

    /**
     * 批量创建经销商与银行账户的关联关系
     * @param dtoList 新增实体
     * @return 是否成功
     */
    @PostMapping("/dbr/createBatch")
    boolean batchCreate(@RequestBody List<DistributorBankAccountRelationDTO> dtoList);

    /**
     * 批量删除经销商与银行的关联关系
     * @param idList 实体ID集合
     * @return 是否成功
     */
    @DeleteMapping("/dbr/deleteBatch/distributorIds")
    boolean deleteBatchByDistributorIds(@RequestBody List<Long> idList);

    /**
     * 根据经销商ID批量删除所有与银行账户的关联关系
     * @param distributorId 经销商ID
     * @return 成功删除的记录数
     */
    @DeleteMapping("/dbr/distributorId/{distributorId}")
    int deleteBatchByDistributorId(@PathVariable(value = "distributorId") Long distributorId);

    /**
     * 根据经销商ID集合查找所有与银行账户的关联关系
     * @param distributorIds 经销商ID集合
     * @return 关联关系集合
     */
    @GetMapping("/dbr/distibutorIds")
    List<DistributorBankAccountRelationDTO> findAllByDistributorIds(@RequestBody List<Long> distributorIds);

}