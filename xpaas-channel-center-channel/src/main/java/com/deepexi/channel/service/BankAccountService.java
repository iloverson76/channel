package com.deepexi.channel.service;

import com.deepexi.channel.domain.bank.BankAccountDTO;
import com.deepexi.channel.domain.eo.CcBankAccount;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_bank_account
 */
public interface BankAccountService {
    List<BankAccountDTO>  saveBatch(List<BankAccountDTO> bankAccountDTOS);

    List<BankAccountDTO> getBankAccountByIds(List<Long> bankAccountIds);

//    /**
//    * 分页获取列表
//    * @param eo
//    * @param page
//    * @param size
//    * @return
//    */
//    PageBean<CcBankAccount> findPage(CcBankAccount eo, Integer page, Integer size);
//    /**
//    * 获取列表
//    * @return
//    */
//    List<CcBankAccount> findAll(CcBankAccount eo);
//
//    /**
//      获取详情
//    * @return
//    */
//    CcBankAccount detail(Integer pk);
//
//    /**
//     更新eo
//    * @param eo
//    * @return
//    */
//    Boolean update(Integer id, CcBankAccount eo);
//
//    /**
//    * 创建eo
//    * @param eo
//    * @return
//    */
//    Boolean create(CcBankAccount eo);
//
//    /**
//     * 单个删除
//    * @return
//    */
//    Boolean delete(Integer pk);
//
    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);
}