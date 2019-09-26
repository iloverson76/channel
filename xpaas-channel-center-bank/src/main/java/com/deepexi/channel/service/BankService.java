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
     * 分页查询银行
     *
     * @param query 查询条件
     * @return 银行列表
     */
    List<BankDTO> findList(BankQuery query);

    /**
     * 查询该应用下所有银行，如果库中未有数据，则初始化
     *
     * @return 银行列表
     */
    List<BankDTO> listBank();

    /**
     * 根据id列表获取银行
     *
     * @param bankIds 银行id列表
     * @return 银行列表
     */
    List<BankDTO> getBankByIds(List<Long> bankIds);

    /**
     * 根据id获取银行
     *
     * @param id
     * @return 银行详情
     */
    BankDTO detail(Long id);

    /**
     * 创建银行
     *
     * @param bankDTO 银行dto
     * @return 新增结果Boolean
     */
    Boolean create(BankDTO bankDTO);

    /**
     * 批量创建银行
     *
     * @param bankDTOS 银行列表
     * @return 新增结果Boolean
     */
    Boolean createBatch(List<BankDTO> bankDTOS);

    /**
     * 根据id列表批量删除银行
     *
     * @param ids id列表
     * @return 删除结果Boolean
     */
    Boolean delete(List<Long> ids);

    /**
     * 根据id删除银行
     *
     * @param id 银行id
     * @return 删除结果Boolean
     */
    Boolean delete(Long id);

    /**
     * 更新银行
     *
     * @param bankDTO 银行dto
     * @return 更新结果Boolean
     */
    Boolean update(BankDTO bankDTO);

    /**
     * 批量更新银行
     *
     * @param bankDTOS 银行dto列表
     * @return 更新结果Boolean
     */
    Boolean updateBatch(List<BankDTO> bankDTOS);
}