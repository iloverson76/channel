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
     * @MethodName: saveBatch
     * @Description: 批量新增银行账户
     * @Param: [bankAccountDTOS]
     * @Return: java.util.List<com.deepexi.channel.domain.BankAccountDTO>
     * @Author: mumu
     * @Date: 2019/9/5
    **/
    List<BankAccountDTO> saveBatch(List<BankAccountDTO> bankAccountDTOS);

    List<BankAccountDTO> findList(BankAccountQuery query);

    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);

    Long create(BankAccountDTO clone);

    Boolean update(BankAccountDTO dto);

    Boolean deleteBankAccounts(List<Long> ids);
}