package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreType;
import com.deepexi.channel.domain.dto.CcStoreTypeDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_store_type
 */
public interface CcStoreTypeService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcStoreType> findPage(CcStoreType eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcStoreType> findAll(CcStoreType eo);

    /**
      获取详情
    * @return
    */
    CcStoreType detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcStoreType eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcStoreType eo);

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