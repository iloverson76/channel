package com.deepexi.channel.service;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;
import com.deepexi.channel.domain.eo.CcChain;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_chain
 */
public interface ChainService {

    /**
    * 分页获取列表
    * @param query
    * @return
    */
    List<ChainDTO> findPage(ChainQuery query);
//    /**
//    * 获取列表
//    * @return
//    */
//    List<CcChain> findAll(CcChain eo);
//
    /**
      获取详情
    * @return
    */
    ChainDTO detail(Long id);

    /**
     更新eo
    * @param dto
    * @return
    */
    Boolean update(ChainDTO dto);
//
//    /**
//    * 创建eo
//    * @param eo
//    * @return
//    */
//    Boolean create(CcChain eo);
//
//    /**
//     * 单个删除
//    * @return
//    */
//    Boolean delete(Integer pk);
//
//    /**
//     批量删除
//    * @return
//    */
//    Boolean delete(Integer... pk);
}