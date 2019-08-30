package com.deepexi.channel.service;

import com.deepexi.channel.domain.chain.ChainTypeDTO;
import com.deepexi.channel.domain.chain.ChainTypeQuery;
import com.deepexi.channel.domain.eo.CcChainType;
import com.deepexi.util.pageHelper.PageBean;

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
    Boolean create(ChainTypeDTO dto);

    /**
     批量删除
    * @return
    */
    Boolean delete(List<Long> ids);
}