package com.deepexi.channel.service;

import com.deepexi.channel.domain.ChainTypeDTO;
import com.deepexi.channel.domain.ChainTypeQuery;

import java.util.List;

/**
 * @author mumu
 * @version 1.0
 * @date 2019/9/19 19:55
 */
public interface ChainTypeService {

    /**
     * 分页获取连琐类型列表
     *
     * @param query 查询条件
     * @return 连琐类型列表
     */
    List<ChainTypeDTO> findPage(ChainTypeQuery query);

    /**
     * 获取连琐类型列表
     *
     * @param query 查询条件
     * @return 连琐类型列表
     */
    List<ChainTypeDTO> findAll(ChainTypeQuery query);

    /**
     * 获取门店类型详情
     *
     * @param id 门店类型id
     * @return 门店类型详情
     */
    ChainTypeDTO detail(Long id);

    /**
     * 更新门店类型
     *
     * @param dto 门店类型dto
     * @return 更新结果boolean
     */
    Boolean update(ChainTypeDTO dto);

    /**
     * 批量更新ChainType
     *
     * @param list 门店类型列表
     * @return 更新结果boolean
     */
    Boolean updateBatch(List<ChainTypeDTO> list);

    /**
     * 创建门店类型
     *
     * @param dto 门店类型dto
     * @return 新增结果boolean
     */
    Long create(ChainTypeDTO dto);

    /**
     * 批量创建门店类型
     *
     * @param dtos 门店类型dto列表
     * @return 新增结果boolean
     */
    Boolean createBatch(List<ChainTypeDTO> dtos);

    /**
     * 批量删除
     *
     * @param ids 门店类型id列表
     * @return 删除结果boolean
     */
    Boolean delete(List<Long> ids);

    /**
     * 判断编码是否重复，具备排除本身功能
     *
     * @param dto 门店类型dto，若新增时调用，id传0
     * @return 编码唯一true，编码不唯一false
     */
    boolean isCodeUnique(ChainTypeDTO dto);

    /**
     * 判断名字是否重复，具备排除本身功能
     *
     * @param dto 门店类型dto，若新增时调用，id传0
     * @return 名字唯一true，名字不唯一false
     */
    boolean isNameUnique(ChainTypeDTO dto);

    /**
     * 删除时，判断id列表是否具有子节点,并且不在ids中
     *
     * @param ids 门店类型id列表
     * @return 存在儿子节点true，不存在儿子节点false
     */
    Boolean haveChildren(List<Long> ids);

    /**
     * 判断dto设置的上级是否合法，限制上级是否合法
     *
     * @param dto 连琐类型dto
     * @return 合法true，非法false
     */
    boolean isParentLegal(ChainTypeDTO dto);

    /**
     * 查询不在chainId的ChainType，用于门店选择
     *
     * @param chainTypeIdList 门店类型id
     * @return 连琐类型列表
     */
    List<ChainTypeDTO> findByChainIdNotInAll(List<Long> chainTypeIdList);

    /**
     * 查询未受分类限制上级-新增用
     *
     * @return 合法的上级连琐分类
     */
    List<ChainTypeDTO> listParentNodesForCreate();

    /**
     * 查询未受分类限制上级-更新用
     *
     * @param id 连琐类型id
     * @return 合法的上级连琐分类
     */
    List<ChainTypeDTO> listParentNodesForUpdate(Long id);

}