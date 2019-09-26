package com.deepexi.channel.service;

import com.deepexi.channel.domain.BankAccountDTO;
import com.deepexi.channel.domain.BankAccountQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
public interface BankAccountService {
    /**
     * 批量新增银行账户
     *
     * @param bankAccountDTOS 银行账户列表
     * @return
     */
    List<BankAccountDTO> saveBatch(List<BankAccountDTO> bankAccountDTOS);

    /**
     * 分页查询银行账户列表
     *
     * @param query 查询条件
     * @return
     */
    List<BankAccountDTO> findList(BankAccountQuery query);

    /**
     * 批量删除
     *
     * @param ids 银行账户id列表
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 创建银行账户
     *
     * @param dto 银行账户dto
     * @return
     */
    Long create(BankAccountDTO dto);

    /**
     * 更新银行账户
     *
     * @param dto 银行账户dto
     * @return
     */
    Boolean update(BankAccountDTO dto);

    /**
     * 批量删除银行账号
     *
     * @param ids 银行账户列表
     * @return
     */
    Boolean deleteBankAccounts(List<Long> ids);
}