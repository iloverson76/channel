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
    Boolean update(Long id, ChainTypeDTO dto);

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
     * 判断id列表是否具有子节点,并且不在ids中
     * @param ids
     * @return
     */
    Boolean haveChildren(List<Long> ids);
}