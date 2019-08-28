package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcBank;
import com.deepexi.channel.domain.dto.CcBankDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_bank
 */
public interface BankService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcBank> findPage(CcBank eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcBank> findAll(CcBank eo);

    /**
      获取详情
    * @return
    */
    CcBank detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcBank eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcBank eo);

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