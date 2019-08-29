package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcChain;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_chain
 */
public interface ChainService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcChain> findPage(CcChain eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcChain> findAll(CcChain eo);

    /**
      获取详情
    * @return
    */
    CcChain detail(Integer pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer id, CcChain eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcChain eo);

    /**
     * 单个删除
    * @return
    */
    Boolean delete(Integer pk);

    /**
     批量删除
    * @return
    */
    Boolean delete(Integer... pk);
}