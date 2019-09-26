package com.deepexi.channel.service;

import com.deepexi.channel.domain.ChainBankDTO;
import com.deepexi.channel.domain.ChainBankQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
public interface ChainBankService {

    /**
     * 根据id查询连琐银行账户关联详情
     *
     * @param id 连琐银行账户关联id
     * @return
     */
    ChainBankDTO detail(Long id);

    /**
     * 更新连琐银行账户关联关系
     *
     * @param dto 连琐银行账户关联dto
     * @return
     */
    boolean update(ChainBankDTO dto);

    /**
     * 批量更新连琐银行账户关联关系
     *
     * @param dtos 连琐银行账户关联dto列表
     * @return
     */
    boolean updateBatch(List<ChainBankDTO> dtos);

    /**
     * 批量删除连琐银行账户关系
     *
     * @param ids 连琐银行账户关联id列表
     * @return
     */
    boolean delete(List<Long> ids);

    /**
     * 分页查询连琐银行账户关联列表
     *
     * @param chainBankQuery 查询条件
     * @return
     */
    List<ChainBankDTO> findList(ChainBankQuery chainBankQuery);

    /**
     * 新增连琐银行账户关联关系
     *
     * @param dto 连琐银行账户关联dto
     * @return
     */
    boolean save(ChainBankDTO dto);

    /**
     * 批量新增连琐银行账户关联关系
     *
     * @param chainBankDTOS 连琐银行账户关联dto列表
     * @return
     */
    boolean saveBatch(List<ChainBankDTO> chainBankDTOS);

    /**
     * 根据连琐id删除连琐银行账户关联关系
     *
     * @param id
     * @return
     */
    boolean deleteByChainId(Long id);

}