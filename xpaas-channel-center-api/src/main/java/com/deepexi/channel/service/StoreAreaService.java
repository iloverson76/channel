package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcStoreArea;
import com.deepexi.channel.domain.dto.CcStoreAreaDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_store_area
 */
public interface StoreAreaService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcStoreArea> findPage(CcStoreArea eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcStoreArea> findAll(CcStoreArea eo);

    /**
      获取详情
    * @return
    */
    CcStoreArea detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcStoreArea eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcStoreArea eo);

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