package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStore;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_store
 */
public interface StoreService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcStore> findPage(CcStore eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcStore> findAll(CcStore eo);

    /**
      获取详情
    * @return
    */
    CcStore detail(Integer pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer id, CcStore eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcStore eo);

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