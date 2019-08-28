package com.deepexi.channel.service;

import com.deepexi.channel.domain.eo.CcArea;
import com.deepexi.channel.domain.dto.CcAreaDto;
import com.deepexi.util.pageHelper.PageBean;
import java.util.List;

/**
 * cc_area
 */
public interface AreaService {

    /**
    * 分页获取列表
    * @param eo
    * @param page
    * @param size
    * @return
    */
    PageBean<CcArea> findPage(CcArea eo, Integer page, Integer size);
    /**
    * 获取列表
    * @return
    */
    List<CcArea> findAll(CcArea eo);

    /**
      获取详情
    * @return
    */
    CcArea detail(Integer  pk);

    /**
     更新eo
    * @param eo
    * @return
    */
    Boolean update(Integer  id,CcArea eo);

    /**
    * 创建eo
    * @param eo
    * @return
    */
    Boolean create(CcArea eo);

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