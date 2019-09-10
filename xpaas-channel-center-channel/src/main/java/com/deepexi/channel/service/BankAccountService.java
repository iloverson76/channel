package com.deepexi.channel.service;

import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.bank.BankAccountQuery;

import java.util.List;

/**
 * cc_bank_account
 */
public interface BankAccountService {
    /**
     * @MethodName: saveBatch
     * @Description: 批量新增银行账户
     * @Param: [bankAccountDTOS]
     * @Return: java.util.List<com.deepexi.channel.domain.bank.BankAccountDTO>
     * @Author: mumu
     * @Date: 2019/9/5
    **/
    List<BankAccountDTO> saveBatch(List<BankAccountDTO> bankAccountDTOS);

//    List<BankAccountDTO> getBankAccountByIds(List<Long> bankAccountIds);

    List<BankAccountDTO> findList(BankAccountQuery query);

    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);

    Long create(BankAccountDTO clone);
}