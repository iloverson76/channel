package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcDistributor;
import com.deepexi.channel.domain.dto.CcDistributorDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_distributor
 */
public interface CcDistributorService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcDistributor> findPage(CcDistributor eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcDistributor> findAll(CcDistributor eo);

    /**
      获取详情
    * @return
    */
    CcDistributor detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcDistributor eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcDistributor eo);

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