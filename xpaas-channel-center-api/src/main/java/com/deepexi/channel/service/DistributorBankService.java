package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcDistributorBank;
import com.deepexi.channel.domain.dto.CcDistributorBankDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_distributor_bank
 */
public interface DistributorBankService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcDistributorBank> findPage(CcDistributorBank eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcDistributorBank> findAll(CcDistributorBank eo);

    /**
      获取详情
    * @return
    */
    CcDistributorBank detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcDistributorBank eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcDistributorBank eo);

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