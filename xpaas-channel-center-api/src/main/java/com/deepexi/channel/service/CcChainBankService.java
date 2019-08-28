package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcChainBank;
import com.deepexi.channel.domain.dto.CcChainBankDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_chain_bank
 */
public interface CcChainBankService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcChainBank> findPage(CcChainBank eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcChainBank> findAll(CcChainBank eo);

    /**
      获取详情
    * @return
    */
    CcChainBank detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcChainBank eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcChainBank eo);

    /**
     * 单个删除
    * @return
    */
    Boolean delete(Integer  pk);

    /**
     批量删除
    * @return
    */
    Boolean delete(Integer ...pk);
}