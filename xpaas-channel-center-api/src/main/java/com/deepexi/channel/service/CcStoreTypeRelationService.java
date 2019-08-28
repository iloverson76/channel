package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreTypeRelation;
import com.deepexi.channel.domain.dto.CcStoreTypeRelationDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_store_type_relation
 */
public interface CcStoreTypeRelationService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcStoreTypeRelation> findPage(CcStoreTypeRelation eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcStoreTypeRelation> findAll(CcStoreTypeRelation eo);

    /**
      获取详情
    * @return
    */
    CcStoreTypeRelation detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcStoreTypeRelation eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcStoreTypeRelation eo);

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