package com.deepexi.channel.service;

import com.deepexi.channel.domain.chain.ChainDTO;
import com.deepexi.channel.domain.chain.ChainQuery;

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

    /**
    * 创建eo
    * @param dto
    * @return
    */
    Long create(ChainDTO dto);
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

    Integer getChainCountByTypeIds(List<Long> ids);

    boolean isCodeUnique(ChainDTO dto);

    boolean haveChildren(List<Long> ids);

    /**
     * 批量更新path，
     * @param chainDTOS
     * @return
     */
    boolean updateBatch(List<ChainDTO> chainDTOS);

    /**
     * 获取整棵树的节点
     * @return
     */
    List<ChainDTO> getChainTreeNode();

    /**
     * 重置所有path和parentId
     * @return
     */
    Boolean resetTree();
}