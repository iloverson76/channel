package com.deepexi.channel.service;

import com.deepexi.channel.domain.ChainDTO;
import com.deepexi.channel.domain.ChainDetailDTO;
import com.deepexi.channel.domain.ChainQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
public interface ChainService {

    /**
     * 分页获取连琐列表
     *
     * @param query 查询条件
     * @return 连琐列表
     */
    List<ChainDTO> findPage(ChainQuery query);

    /**
     * 获取连琐详情
     *
     * @param id 连琐id
     * @return 连琐详情
     */
    ChainDTO detail(Long id);

    /**
     * 更新连琐
     *
     * @param dto 连琐dto
     * @return 修改结果 Boolean
     */
    Boolean update(ChainDTO dto);

    /**
     * 批量更新连琐
     *
     * @param dtos 连琐dto列表
     * @return 修改结果 Boolean
     */
    Boolean updateBatch(List<ChainDTO> dtos);

    /**
     * 创建连琐
     *
     * @param dto 连琐dto
     * @return 连琐id
     */
    Long create(ChainDTO dto);

    /**
     * 批量创建连琐
     *
     * @param dtos 连琐dto列表
     * @return 新增结果 Boolean
     */
    Boolean createBatch(List<ChainDTO> dtos);
//

    /**
     * 批量删除
     *
     * @param ids 连琐id列表
     * @return 删除结果 Boolean
     */
    Boolean delete(List<Long> ids);

    /**
     * 编码是否唯一，更新时会排除本身
     *
     * @param dto 连琐dto，对于新增的dto没有id时，id传0
     * @return true编码唯一，false编码不唯一
     */
    boolean isCodeUnique(ChainDTO dto);

    /**
     * 删除时，判断是否具有儿子节点，若子节点出现在id列表中，视为没儿子节点，有返回true， 没有返回false
     *
     * @param ids 连琐id列表
     * @return 没有子节点false，有子节点true
     */
    boolean haveChildren(List<Long> ids);

    /**
     * 批量更新path 和parentId，用于连琐树形结构用
     *
     * @param chainDTOS 连琐dto列表
     * @return 更新结果 Boolean
     */
    boolean updatePathAndParentIdBatch(List<ChainDTO> chainDTOS);

    /**
     * 获取整棵树的节点，展示三层的整棵树节点
     *
     * @return 连琐列表
     */
    List<ChainDTO> getChainTreeNode();

    /**
     * 重置所有path和parentId
     *
     * @return 更新结果Boolean
     */
    Boolean resetTree();

    /**
     * 判断名称是否重复
     *
     * @param dto
     * @return true重复，false不重复
     */
    boolean isNameUnique(ChainDetailDTO dto);

    /**
     * 更新连琐的path和parentId，修改或新增树节点用
     *
     * @param chainDTO
     * @return 更新结果Boolean
     */
    Boolean updatePathAndParentId(ChainDTO chainDTO);

}