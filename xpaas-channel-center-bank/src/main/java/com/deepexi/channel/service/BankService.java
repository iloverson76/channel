package com.deepexi.channel.service;

import com.deepexi.channel.domain.BankDTO;
import com.deepexi.channel.domain.BankQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 17:10
 */
public interface BankService {

    /**
     * 创建银行
     *
     * @param bankDTO 银行dto
     * @return
     */
    Boolean create(BankDTO bankDTO);

    /**
     * 分页查询银行
     *
     * @param query 查询条件
     * @return
     */
    List<BankDTO> findList(BankQuery query);

    /**
     * 查询该应用下所有银行，如果库中未有数据，则初始化
     *
     * @return
     */
    List<BankDTO> listBank();

    /**
     * 根据id列表获取银行
     *
     * @param bankIds 银行id列表
     * @return
     */
    List<BankDTO> getBankByIds(List<Long> bankIds);

    /**
     * 批量创建银行
     *
     * @param bankDTOS 银行列表
     * @return
     */
    Boolean createBatch(List<BankDTO> bankDTOS);

    /**
     * 根据id列表批量删除银行
     *
     * @param ids id列表
     * @return
     */
    Boolean delete(List<Long> ids);

    /**
     * 根据id删除银行
     *
     * @param id 银行id
     * @return
     */
    Boolean delete(Long id);

    /**
     * 更新银行
     *
     * @param bankDTO 银行dto
     * @return
     */
    Boolean update(BankDTO bankDTO);

    /**
     * 批量更新银行
     *
     * @param bankDTOS 银行dto列表
     * @return
     */
    Boolean updateBatch(List<BankDTO> bankDTOS);
}