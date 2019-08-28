package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcChainType;
import com.deepexi.channel.domain.dto.CcChainTypeDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_chain_type
 */
public interface CcChainTypeService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcChainType> findPage(CcChainType eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcChainType> findAll(CcChainType eo);

    /**
      获取详情
    * @return
    */
    CcChainType detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcChainType eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcChainType eo);

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