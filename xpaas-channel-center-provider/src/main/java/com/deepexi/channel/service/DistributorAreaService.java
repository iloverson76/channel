package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcDistributorArea;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * cc_distributor_area
 */
public interface DistributorAreaService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcDistributorArea> findPage(CcDistributorArea eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcDistributorArea> findAll(CcDistributorArea eo);

    /**
      获取详情
    * @return
    */
    CcDistributorArea detail(Integer pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer id, CcDistributorArea eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcDistributorArea eo);

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