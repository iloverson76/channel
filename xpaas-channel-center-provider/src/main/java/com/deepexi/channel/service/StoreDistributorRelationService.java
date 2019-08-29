package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreDistributorRelation;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_store_distributor_relation
 */
public interface StoreDistributorRelationService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcStoreDistributorRelation> findPage(CcStoreDistributorRelation eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcStoreDistributorRelation> findAll(CcStoreDistributorRelation eo);

    /**
      获取详情
    * @return
    */
    CcStoreDistributorRelation detail(Integer pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer id, CcStoreDistributorRelation eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcStoreDistributorRelation eo);

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