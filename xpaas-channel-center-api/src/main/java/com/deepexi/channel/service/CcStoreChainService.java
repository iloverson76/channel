package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreChain;
import com.deepexi.channel.domain.dto.CcStoreChainDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_store_chain
 */
public interface CcStoreChainService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcStoreChain> findPage(CcStoreChain eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcStoreChain> findAll(CcStoreChain eo);

    /**
      获取详情
    * @return
    */
    CcStoreChain detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcStoreChain eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcStoreChain eo);

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