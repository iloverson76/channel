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
     * @return 连琐银行账户关联详情
     */
    ChainBankDTO detail(Long id);

    /**
     * 更新连琐银行账户关联关系
     *
     * @param dto 连琐银行账户关联dto
     * @return 更新结果Boolean
     */
    boolean update(ChainBankDTO dto);

    /**
     * 批量更新连琐银行账户关联关系
     *
     * @param dtos 连琐银行账户关联dto列表
     * @return 更新结果Boolean
     */
    boolean updateBatch(List<ChainBankDTO> dtos);

    /**
     * 批量删除连琐银行账户关系
     *
     * @param ids 连琐银行账户关联id列表
     * @return 删除结果Boolean
     */
    boolean delete(List<Long> ids);

    /**
     * 分页查询连琐银行账户关联列表
     *
     * @param chainBankQuery 查询条件
     * @return 连琐银行账户关联列表
     */
    List<ChainBankDTO> findList(ChainBankQuery chainBankQuery);

    /**
     * 新增连琐银行账户关联关系
     *
     * @param dto 连琐银行账户关联dto
     * @return 新增结果Boolean
     */
    boolean save(ChainBankDTO dto);

    /**
     * 批量新增连琐银行账户关联关系
     *
     * @param chainBankDTOS 连琐银行账户关联dto列表
     * @return 新增结果Boolean
     */
    boolean saveBatch(List<ChainBankDTO> chainBankDTOS);

    /**
     * 根据连琐id删除连琐银行账户关联关系
     *
     * @param id
     * @return 删除结果Boolean
     */
    boolean deleteByChainId(Long id);

}