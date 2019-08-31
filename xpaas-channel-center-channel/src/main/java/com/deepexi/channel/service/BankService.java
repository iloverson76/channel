package com.deepexi.channel.service;

import com.deepexi.channel.domain.bank.BankDO;
import com.deepexi.channel.domain.bank.BankDTO;

import java.util.List;

/**
 * cc_bank
 */
public interface BankService {

//    /**
//    * 分页获取列表
//    * @param eo
//    * @param page
//    * @param size
//    * @return
//    */
//    PageBean<CcBank> findPage(CcBank eo, Integer page, Integer size);
//    /**
//    * 获取列表
//    * @return
//    */
//    List<CcBank> findAll(CcBank eo);
//
//    /**
//      获取详情
//    * @return
//    */
//    CcBank detail(Integer  pk);
//
//    /**
//     更新eo
//    * @param eo
//    * @return
//    */
//    Boolean update(Integer  id,CcBank eo);
//
    /**
    * 创建eo
    * @param bankDTO
    * @return
    */
    Boolean create(BankDTO bankDTO);
//
//    /**
//     * 单个删除
//    * @return
//    */
//    Boolean delete(Integer  pk);
//
//    /**
//     批量删除
//    * @return
//    */
//    Boolean delete(Integer ...pk);

    List<BankDTO> listBank();


    List<BankDTO> getBankByIds(List<Long> bankIds);
}