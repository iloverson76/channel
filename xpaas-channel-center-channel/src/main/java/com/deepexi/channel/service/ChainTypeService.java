package com.deepexi.channel.service;

import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;

import java.util.List;

/**
 * cc_chain_type
 */
public interface ChainTypeService {

    /**
    * 分页获取列表
    * @param query
    * @return
    */
    List<ChainTypeDTO> findPage(ChainTypeQuery query);
    /**
    * 获取列表
    * @return
    */
    List<ChainTypeDTO> findAll(ChainTypeQuery query);

//    ChainTypeDTO getChainType(Integer id);

    /**
      获取详情
    * @return
    */
    ChainTypeDTO detail(Long id);

    /**
     更新eo
    * @param dto
    * @return
    */
    Boolean update(ChainTypeDTO dto);

    /**
    * 创建eo
    * @param dto
    * @return
    */
    Long create(ChainTypeDTO dto);

    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);

    /**
     * 判断编码是否重复，具备排除本身功能
     * @param dto
     * @return
     */
    boolean isCodeUnique(ChainTypeDTO dto);
    /**
     * @MethodName: isNameUnique
     * @Description: 判断名字是否重复，具备排除本身功能
     * @Param: [dto]
     * @Return: boolean
     * @Author: mumu
     * @Date: 2019/9/10
    **/
    boolean isNameUnique(ChainTypeDTO dto);

    /**
     * 判断id列表是否具有子节点,并且不在ids中
     * @param ids
     * @return
     */
    Boolean haveChildren(List<Long> ids);

    /**
     * 判断dto设置的上级是否合法，限制上级是否合法
     * @param dto
     * @return
     */
    boolean isParentLegal(ChainTypeDTO dto);

    /**
     * 查询不在chainId的ChainType
     */
    List<ChainTypeDTO> findByChainIdNotInAll(List<Long> chainIdList);

    /**
     * 批量更新ChainType
     * @param list
     * @return
     */
    Boolean updateBatch(List<ChainTypeDTO> list);

    List<ChainTypeDTO> listParentNodesForCreate();

    List<ChainTypeDTO> listParentNodesForUpdate(Long id);
}